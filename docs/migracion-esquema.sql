-- Revisar y respaldar antes de aplicar sobre una base existente.
-- Conserva la columna histÃ³rica mantenimiento.id_empeado y la tabla puente antigua.
BEGIN;
ALTER TABLE cliente ALTER COLUMN dni TYPE varchar(255) USING dni::text;
ALTER TABLE cliente ALTER COLUMN telefono TYPE varchar(255) USING telefono::text;
ALTER TABLE empleado ALTER COLUMN dni TYPE varchar(255) USING dni::text;
ALTER TABLE empleado ALTER COLUMN telefono TYPE varchar(255) USING telefono::text;
ALTER TABLE penalidad ALTER COLUMN monto TYPE numeric(10,2) USING monto::numeric(10,2);
-- Hibernate puede haber generado nombres diferentes para las restricciones Ãºnicas.
DO $$
DECLARE r record;
BEGIN
 FOR r IN SELECT c.conname, c.conrelid::regclass AS tabla
 FROM pg_constraint c
 JOIN pg_attribute a ON a.attrelid=c.conrelid AND a.attnum=c.conkey[1]
 WHERE c.contype='u' AND array_length(c.conkey,1)=1
 AND ((c.conrelid='devolucion'::regclass AND a.attname IN ('fecha_devolucion','estado_prenda'))
 OR (c.conrelid='penalidad'::regclass AND a.attname IN ('tipo_penalidad','descripcion')))
 LOOP EXECUTE format('ALTER TABLE %s DROP CONSTRAINT %I',r.tabla,r.conname); END LOOP;
END $$;
ALTER TABLE mantenimiento RENAME COLUMN id_empeado TO id_empleado;
ALTER TABLE cliente DROP CONSTRAINT IF EXISTS cliente_telefono_key;
ALTER TABLE empleado DROP CONSTRAINT IF EXISTS empleado_telefono_key;
DO $$ BEGIN
 IF EXISTS (SELECT 1 FROM devolucion_penalidades) THEN
  RAISE EXCEPTION 'La tabla puente contiene datos: conciliar antes de eliminar';
 END IF;
END $$;
DROP TABLE devolucion_penalidades;
COMMIT;
