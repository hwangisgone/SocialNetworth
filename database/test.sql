PGDMP      *                |            PURRPOST    16.1    16.1 	    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    17079    PURRPOST    DATABASE     �   CREATE DATABASE "PURRPOST" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'English_United States.1252';
    DROP DATABASE "PURRPOST";
                postgres    false            �            1259    17081 	   tutorials    TABLE     �   CREATE TABLE public.tutorials (
    published boolean,
    id bigint NOT NULL,
    description character varying(255),
    title character varying(255)
);
    DROP TABLE public.tutorials;
       public         heap    postgres    false            �            1259    17080    tutorials_seq    SEQUENCE     w   CREATE SEQUENCE public.tutorials_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.tutorials_seq;
       public          postgres    false            �          0    17081 	   tutorials 
   TABLE DATA           F   COPY public.tutorials (published, id, description, title) FROM stdin;
    public          postgres    false    216   �       �           0    0    tutorials_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.tutorials_seq', 1, false);
          public          postgres    false    215                       2606    17087    tutorials tutorials_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.tutorials
    ADD CONSTRAINT tutorials_pkey PRIMARY KEY (id);
 B   ALTER TABLE ONLY public.tutorials DROP CONSTRAINT tutorials_pkey;
       public            postgres    false    216            �      x������ � �     