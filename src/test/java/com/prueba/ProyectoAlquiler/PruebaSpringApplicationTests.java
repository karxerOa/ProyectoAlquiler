package com.prueba.ProyectoAlquiler;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.beans.factory.annotation.Autowired;
import com.prueba.ProyectoAlquiler.model.*;
import com.prueba.ProyectoAlquiler.repository.*;
import com.prueba.ProyectoAlquiler.service.interfaz.IUsuarioService;
import java.net.*;
import java.net.http.*;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class PruebaSpringApplicationTests {
 @LocalServerPort int port;
 @Autowired IRolRepository roles;
 @Autowired IUsuarioService users;
 @Autowired com.prueba.ProyectoAlquiler.security.IUserRepository userRepo;
 @Autowired org.springframework.security.crypto.password.PasswordEncoder encoder;
 final HttpClient client=HttpClient.newHttpClient();
 String token;
 HttpResponse<String> call(String method,String path,String body,String bearer) throws Exception {
  var b=HttpRequest.newBuilder(URI.create("http://localhost:"+port+path)).header("Content-Type","application/json");
  if(bearer!=null)b.header("Authorization","Bearer "+bearer);
  return client.send(b.method(method,body==null?HttpRequest.BodyPublishers.noBody():HttpRequest.BodyPublishers.ofString(body)).build(),HttpResponse.BodyHandlers.ofString());
 }
 int id(String body,String field){var m=java.util.regex.Pattern.compile("\""+field+"\"\\s*:\\s*(\\d+)").matcher(body);assertTrue(m.find(),body);return Integer.parseInt(m.group(1));}
 String login(String username) throws Exception {
  var r=call("POST","/login","{\"username\":\""+username+"\",\"password\":\"TestPassword123!\"}",null);
  assertEquals(200,r.statusCode(),r.body());
  return r.body().split("\"")[3];
 }
 @Test void completeHttpFlow() throws Exception {
  for(String name:new String[]{"ADMIN","USER","EMPLEADO"}){Rol r=new Rol();r.setName(name);r.setDescription(name);roles.save(r);}
  Usuario admin=new Usuario();admin.setUsername("testadmin");admin.setPassword("TestPassword123!");admin.setEnabled(true);admin.setRol(roles.findAll().stream().filter(r->r.getName().equals("ADMIN")).findFirst().orElseThrow());users.save(admin);
  assertTrue(encoder.matches("TestPassword123!",userRepo.findOneByUsername("testadmin").getPassword()));
  assertEquals(401,call("GET","/v1/categorias",null,null).statusCode());
  assertEquals(401,call("GET","/v1/categorias",null,"broken").statusCode());
  assertEquals(401,call("POST","/login","{\"username\":\"testadmin\",\"password\":\"wrong\"}",null).statusCode());
  token=login("testadmin");
  String expired=io.jsonwebtoken.Jwts.builder().subject("testadmin").expiration(new java.util.Date(0)).signWith(io.jsonwebtoken.security.Keys.hmacShaKeyFor("only-for-isolated-integration-tests-32-bytes-or-more".getBytes(java.nio.charset.StandardCharsets.UTF_8))).compact();
  assertEquals(401,call("GET","/v1/categorias",null,expired).statusCode());
  String forged=io.jsonwebtoken.Jwts.builder().subject("testadmin").expiration(new java.util.Date(System.currentTimeMillis()+60000)).signWith(io.jsonwebtoken.security.Keys.hmacShaKeyFor("another-incorrect-signing-key-with-at-least-32-bytes".getBytes(java.nio.charset.StandardCharsets.UTF_8))).compact();
  assertEquals(401,call("GET","/v1/categorias",null,forged).statusCode());
  assertEquals(401, call("POST", "/registro", "{\"username\":\"reader\"}", null).statusCode());
  assertNull(userRepo.findOneByUsername("reader"));
  var legacy = new Usuario();
  legacy.setUsername("reader");
  legacy.setPassword(encoder.encode("TestPassword123!"));
  legacy.setEnabled(true);
  legacy.setRol(roles.findAll().stream().filter(r -> "USER".equals(r.getName())).findFirst().orElseThrow());
  userRepo.save(legacy);
  assertEquals(401, call("POST", "/login", "{\"username\":\"reader\",\"password\":\"TestPassword123!\"}", null).statusCode());
  String legacyToken = io.jsonwebtoken.Jwts.builder().subject("reader").expiration(new java.util.Date(System.currentTimeMillis()+60000))
          .signWith(io.jsonwebtoken.security.Keys.hmacShaKeyFor("only-for-isolated-integration-tests-32-bytes-or-more".getBytes(java.nio.charset.StandardCharsets.UTF_8))).compact();
  assertEquals(401, call("GET", "/v1/clientes", null, legacyToken).statusCode());
  assertEquals(400, call("POST", "/v1/usuarios", "{\"username\":\"invalid\",\"password\":\"TestPassword123!\",\"rolId\":" + legacy.getRol().getIdRol() + "}", token).statusCode());
  assertEquals(404,call("GET","/v1/categorias/999999",null,token).statusCode());
  assertEquals(400,call("POST","/v1/categorias","{",token).statusCode());
  assertEquals(200,call("GET","/v3/api-docs",null,null).statusCode());
  var preflight=client.send(HttpRequest.newBuilder(URI.create("http://localhost:"+port+"/v1/categorias")).header("Origin","http://localhost:4200").header("Access-Control-Request-Method","POST").method("OPTIONS",HttpRequest.BodyPublishers.noBody()).build(),HttpResponse.BodyHandlers.ofString());
  assertEquals(200,preflight.statusCode());assertEquals("http://localhost:4200",preflight.headers().firstValue("Access-Control-Allow-Origin").orElse(""));
  String[][] data=new String[][]{{"categorias","idCategoria","{\"nombre\": \"Trajes\", \"descripcion\": \"Prueba\", \"estado\": \"ACTIVO\"}"},{"clientes","idCliente","{\"nombre\": \"Ana\", \"apellidos\": \"Perez\", \"dni\": \"01234567\", \"telefono\": \"987654321\", \"direccion\": \"Lima\", \"correo\": \"test@example.com\"}"},{"empleados","idEmpleado","{\"nombres\": \"Luis\", \"apellidos\": \"Perez\", \"dni\": \"12345678\", \"telefono\": \"987654322\", \"cargo\": \"Atencion\", \"estado\": \"ACTIVO\", \"username\": \"testemployee\", \"password\": \"TestPassword123!\"}"},{"roles","idRol","{\"name\": \"AUDITOR\", \"description\": \"Prueba CRUD\"}"},{"usuarios","idUser","{\"username\": \"cruduser\", \"password\": \"TestPassword123!\", \"enabled\": true, \"rolId\": \"@adminRole@\"}"},{"prendas","idPrenda","{\"nombre\": \"Traje\", \"descripcion\": \"Prueba\", \"talla\": \"M\", \"color\": \"Azul\", \"precioAlquiler\": 50, \"estado\": \"DISPONIBLE\", \"categoriaId\": \"@categorias@\"}"},{"alquileres","idAlquiler","{\"fechaAlquiler\": \"2026-11-11\", \"fechaInicio\": \"2026-11-12\", \"fechaFin\": \"2026-11-13\", \"estado\": \"RESERVADO\", \"subtotal\": 50, \"total\": 50, \"empleadoId\": \"@empleados@\", \"clienteId\": \"@clientes@\"}"},{"detalles-alquiler","idDetalle","{\"cantidad\": 1, \"precioUnitario\": 50, \"subtotal\": 50, \"alquilerId\": \"@alquileres@\", \"prendaId\": \"@prendas@\"}"},{"pagos","idPago","{\"fechaPago\": \"2026-11-11\", \"monto\": 50, \"metodoPago\": \"EFECTIVO\", \"estado\": \"PAGADO\", \"alquilerId\": \"@alquileres@\"}"},{"devoluciones","idDevolucion","{\"fechaDevolucion\": \"2026-11-13T18:00:00\", \"estadoPrenda\": \"BUENO\", \"observacion\": \"Sin problemas\", \"alquilerId\": \"@alquileres@\", \"empleadoId\": \"@empleados@\"}"},{"penalidades","idPenalidad","{\"tipo_penalidad\": \"RETRASO\", \"descripcion\": \"Un dia\", \"monto\": 10.5, \"fecha_penalidad\": \"2026-11-13T18:00:00\", \"estado\": true, \"devolucionId\": \"@devoluciones@\"}"},{"mantenimientos","idMantenimiento","{\"fechaInicio\": \"2026-11-14\", \"fechaFin\": \"2026-11-15\", \"tipoMantenimiento\": \"LAVADO\", \"descripcion\": \"Lavado\", \"costo\": 10, \"estado\": \"PENDIENTE\", \"prendaId\": \"@prendas@\", \"empleadoId\": \"@empleados@\"}"},{"producciones","idProduccion","{\"fechaProduccion\": \"2026-11-11\", \"cantidad\": 1, \"costoProduccion\": 100, \"descripcion\": \"Confeccion\", \"idPrenda\": \"@prendas@\", \"idEmpleado\": \"@empleados@\"}"}};
  java.util.Map<String,Integer> ids=new java.util.HashMap<>();
  ids.put("adminRole",roles.findAll().stream().filter(r -> "ADMIN".equals(r.getName())).findFirst().orElseThrow().getIdRol());
  ids.put("employeeRole",roles.findAll().stream().filter(r -> "EMPLEADO".equals(r.getName())).findFirst().orElseThrow().getIdRol());
  assertEquals(400,call("POST","/v1/usuarios","{\"username\":\"crudemployee\",\"password\":\"TestPassword123!\",\"enabled\":true,\"rolId\":"+ids.get("employeeRole")+"}",token).statusCode());
  for(String[] row:data){
   String body=row[2];for(var e:ids.entrySet())body=body.replace("@"+e.getKey()+"@",e.getValue().toString());
   var created=call("POST","/v1/"+row[0],body,token);assertEquals(201,created.statusCode(),row[0]+created.body());
   int key=id(created.body(),row[1]);ids.put(row[0],key);
   assertFalse(created.body().contains("password"));
   assertEquals(200,call("GET","/v1/"+row[0]+"/"+key,null,token).statusCode());
   var updated=call("PUT","/v1/"+row[0]+"/"+key,body,token);assertEquals(200,updated.statusCode(),updated.body());assertEquals(key,id(updated.body(),row[1]),row[0]);
   assertEquals(404,call("PUT","/v1/"+row[0]+"/999999",body,token).statusCode());
   assertEquals(200,call("GET","/v1/"+row[0],null,token).statusCode());
  }
  String staff = login("testemployee");
  assertEquals(404,call("GET","/v1/empleados/me",null,token).statusCode());
  assertEquals(200,call("GET","/v1/empleados/me",null,staff).statusCode());
  assertEquals(200, call("GET", "/v1/alquileres", null, staff).statusCode());
  var staffRental = call("POST", "/v1/alquileres",
          "{\"fechaAlquiler\":\"2026-11-11\",\"fechaInicio\":\"2026-11-12\",\"fechaFin\":\"2026-11-13\",\"estado\":\"RESERVADO\",\"subtotal\":50,\"total\":50,\"empleadoId\":999999,\"clienteId\":"+ids.get("clientes")+"}", staff);
  assertEquals(201, staffRental.statusCode(), staffRental.body());
  assertEquals(ids.get("empleados"), id(staffRental.body(), "empleadoId"));
  assertEquals(204, call("DELETE", "/v1/alquileres/"+id(staffRental.body(), "idAlquiler"), null, token).statusCode());
  assertEquals(403, call("GET", "/v1/usuarios", null, staff).statusCode());
  for(int i=data.length-1;i>=0;i--){String path="/v1/"+data[i][0]+"/"+ids.get(data[i][0]);var d=call("DELETE",path,null,token);assertEquals(204,d.statusCode(),d.body());assertTrue(d.body().isEmpty());assertEquals(404,call("DELETE",path,null,token).statusCode());}
  var u=userRepo.findOneByUsername("testemployee");u.setEnabled(false);userRepo.save(u);
  assertEquals(401,call("GET","/v1/categorias",null,staff).statusCode());
 }
}
