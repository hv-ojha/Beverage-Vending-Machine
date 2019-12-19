CREATE SEQUENCE public.hibernate_sequence;

ALTER SEQUENCE public.hibernate_sequence
    OWNER TO postgres;

DROP TABLE public.inventories;

CREATE TABLE public.inventories
(
    inventory_id bigint NOT NULL,
    name character varying(255) COLLATE pg_catalog."default",
    quantity integer,
    CONSTRAINT inventories_pkey PRIMARY KEY (inventory_id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.inventories
    OWNER to postgres;

INSERT INTO public.inventories values(1,'Sugar',4);
INSERT INTO public.inventories values(2,'Water',19);
INSERT INTO public.inventories values(3,'Coffee',10);
INSERT INTO public.inventories values(4,'Milk',10);

DROP TABLE public.beverage;

CREATE TABLE public.beverage
(
    beverage_id bigint NOT NULL,
    available boolean NOT NULL,
    name character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT beverage_pkey PRIMARY KEY (beverage_id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.beverage
    OWNER to postgres;

INSERT INTO public.beverage values(1,true,'Black Coffee');
INSERT INTO public.beverage values(2,true,'Coffee with Milk');
INSERT INTO public.beverage values(3,true,'Sugarfree Black Coffee');
INSERT INTO public.beverage values(4,true,'Sugarfree Coffee with Milk');

DROP TABLE public.ingredients;

CREATE TABLE public.ingredients
(
    ingredient_id bigint NOT NULL,
    quantity_required integer,
    beverage_beverage_id bigint,
    inventories_inventory_id bigint,
    CONSTRAINT ingredients_pkey PRIMARY KEY (ingredient_id),
    CONSTRAINT fk5hjd461ks2l9asox9cf4i1e0t FOREIGN KEY (inventories_inventory_id)
        REFERENCES public.inventories (inventory_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fks52hacvxttyuqu7gommkcwnya FOREIGN KEY (beverage_beverage_id)
        REFERENCES public.beverage (beverage_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.ingredients
    OWNER to postgres;

INSERT INTO public.ingredients values(1,1,1,1);
INSERT INTO public.ingredients values(2,1,1,3);
INSERT INTO public.ingredients values(3,3,1,2);

INSERT INTO public.ingredients values(4,1,2,2);
INSERT INTO public.ingredients values(5,1,2,3);
INSERT INTO public.ingredients values(6,2,2,4);
INSERT INTO public.ingredients values(7,1,2,1);

INSERT INTO public.ingredients values(8,3,3,2);
INSERT INTO public.ingredients values(9,1,3,3);

INSERT INTO public.ingredients values(10,1,4,2);
INSERT INTO public.ingredients values(11,1,4,3);
INSERT INTO public.ingredients values(12,2,4,4);