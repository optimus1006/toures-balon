﻿// <auto-generated />
using System;
using Javeriana.Convenios.Api.Models;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Infrastructure;
using Microsoft.EntityFrameworkCore.Storage.ValueConversion;
using Npgsql.EntityFrameworkCore.PostgreSQL.Metadata;

namespace Javeriana.Convenios.Api.Migrations
{
    [DbContext(typeof(RepositoryContext))]
    partial class RepositoryContextModelSnapshot : ModelSnapshot
    {
        protected override void BuildModel(ModelBuilder modelBuilder)
        {
#pragma warning disable 612, 618
            modelBuilder
                .HasAnnotation("Npgsql:ValueGenerationStrategy", NpgsqlValueGenerationStrategy.IdentityByDefaultColumn)
                .HasAnnotation("ProductVersion", "3.1.4")
                .HasAnnotation("Relational:MaxIdentifierLength", 63);

            modelBuilder.Entity("Javeriana.Convenios.Api.Models.Ciudad", b =>
                {
                    b.Property<int>("Codigo")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("integer")
                        .HasAnnotation("Npgsql:ValueGenerationStrategy", NpgsqlValueGenerationStrategy.IdentityByDefaultColumn);

                    b.Property<string>("Nombre")
                        .HasColumnType("text");

                    b.Property<int>("PaisCodigo")
                        .HasColumnType("integer");

                    b.HasKey("Codigo");

                    b.HasIndex("PaisCodigo");

                    b.ToTable("Ciudad");
                });

            modelBuilder.Entity("Javeriana.Convenios.Api.Models.Convenio", b =>
                {
                    b.Property<string>("Identificacion")
                        .HasColumnType("text");

                    b.Property<int>("CiudadCodigo")
                        .HasColumnType("integer");

                    b.Property<string>("Correo")
                        .HasColumnType("text");

                    b.Property<string>("Endpoint")
                        .HasColumnType("text");

                    b.Property<int?>("EstadoConvenio")
                        .HasColumnType("integer");

                    b.Property<DateTime?>("FechaVigencia")
                        .HasColumnType("timestamp without time zone");

                    b.Property<string>("NombreProveedor")
                        .HasColumnType("text");

                    b.Property<string>("TemplateEntrada")
                        .HasColumnType("text");

                    b.Property<string>("TemplateSalida")
                        .HasColumnType("text");

                    b.Property<int?>("TipoConvenio")
                        .HasColumnType("integer");

                    b.HasKey("Identificacion");

                    b.HasIndex("CiudadCodigo");

                    b.ToTable("Convenio");
                });

            modelBuilder.Entity("Javeriana.Convenios.Api.Models.Pais", b =>
                {
                    b.Property<int>("Codigo")
                        .ValueGeneratedOnAdd()
                        .HasColumnType("integer")
                        .HasAnnotation("Npgsql:ValueGenerationStrategy", NpgsqlValueGenerationStrategy.IdentityByDefaultColumn);

                    b.Property<string>("Nombre")
                        .HasColumnType("text");

                    b.HasKey("Codigo");

                    b.ToTable("Pais");
                });

            modelBuilder.Entity("Javeriana.Convenios.Api.Models.Ciudad", b =>
                {
                    b.HasOne("Javeriana.Convenios.Api.Models.Pais", "Pais")
                        .WithMany()
                        .HasForeignKey("PaisCodigo")
                        .OnDelete(DeleteBehavior.Cascade)
                        .IsRequired();
                });

            modelBuilder.Entity("Javeriana.Convenios.Api.Models.Convenio", b =>
                {
                    b.HasOne("Javeriana.Convenios.Api.Models.Ciudad", "Ciudad")
                        .WithMany()
                        .HasForeignKey("CiudadCodigo")
                        .OnDelete(DeleteBehavior.Cascade)
                        .IsRequired();
                });
#pragma warning restore 612, 618
        }
    }
}
