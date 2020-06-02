using System;
using Microsoft.EntityFrameworkCore.Migrations;
using Npgsql.EntityFrameworkCore.PostgreSQL.Metadata;

namespace Javeriana.Convenios.Api.Migrations
{
    public partial class InitialCreate : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.CreateTable(
                name: "Pais",
                columns: table => new
                {
                    Codigo = table.Column<int>(nullable: false)
                        .Annotation("Npgsql:ValueGenerationStrategy", NpgsqlValueGenerationStrategy.IdentityByDefaultColumn),
                    Nombre = table.Column<string>(nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Pais", x => x.Codigo);
                });

            migrationBuilder.CreateTable(
                name: "Ciudad",
                columns: table => new
                {
                    Codigo = table.Column<int>(nullable: false)
                        .Annotation("Npgsql:ValueGenerationStrategy", NpgsqlValueGenerationStrategy.IdentityByDefaultColumn),
                    Nombre = table.Column<string>(nullable: true),
                    PaisCodigo = table.Column<int>(nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Ciudad", x => x.Codigo);
                    table.ForeignKey(
                        name: "FK_Ciudad_Pais_PaisCodigo",
                        column: x => x.PaisCodigo,
                        principalTable: "Pais",
                        principalColumn: "Codigo",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.CreateTable(
                name: "Convenio",
                columns: table => new
                {
                    Identificacion = table.Column<string>(nullable: false),
                    NombreProveedor = table.Column<string>(nullable: true),
                    TipoConvenio = table.Column<int>(nullable: true),
                    FechaVigencia = table.Column<DateTime>(nullable: true),
                    Correo = table.Column<string>(nullable: true),
                    CiudadCodigo = table.Column<int>(nullable: false),
                    Endpoint = table.Column<string>(nullable: true),
                    TemplateEntrada = table.Column<string>(nullable: true),
                    TemplateSalida = table.Column<string>(nullable: true),
                    EstadoConvenio = table.Column<int>(nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Convenio", x => x.Identificacion);
                    table.ForeignKey(
                        name: "FK_Convenio_Ciudad_CiudadCodigo",
                        column: x => x.CiudadCodigo,
                        principalTable: "Ciudad",
                        principalColumn: "Codigo",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.CreateIndex(
                name: "IX_Ciudad_PaisCodigo",
                table: "Ciudad",
                column: "PaisCodigo");

            migrationBuilder.CreateIndex(
                name: "IX_Convenio_CiudadCodigo",
                table: "Convenio",
                column: "CiudadCodigo");
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropTable(
                name: "Convenio");

            migrationBuilder.DropTable(
                name: "Ciudad");

            migrationBuilder.DropTable(
                name: "Pais");
        }
    }
}
