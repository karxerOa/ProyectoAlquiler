-- Ejecutar una sola vez en PostgreSQL antes de desplegar el backend actualizado.
-- id_user queda NULL para las fichas de empleado existentes.
BEGIN;

ALTER TABLE empleado ADD COLUMN IF NOT EXISTS id_user integer NULL;
ALTER TABLE empleado ALTER COLUMN id_user DROP NOT NULL;

CREATE UNIQUE INDEX IF NOT EXISTS uk_empleado_id_user
    ON empleado (id_user);

DO $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM pg_constraint
        WHERE conname = 'fk_empleado_usuario'
          AND conrelid = 'empleado'::regclass
    ) THEN
        ALTER TABLE empleado
            ADD CONSTRAINT fk_empleado_usuario
            FOREIGN KEY (id_user) REFERENCES usuario (id_user);
    END IF;
END $$;

COMMIT;
