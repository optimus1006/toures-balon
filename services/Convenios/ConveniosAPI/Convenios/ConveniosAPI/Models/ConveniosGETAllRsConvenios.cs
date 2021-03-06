/*
 * Convenios API
 *
 * Este es el API de Convenios para Toures Balon
 *
 * OpenAPI spec version: 1.0.0
 * Contact: touresbalon@archetype.com
 * Generated by: https://github.com/swagger-api/swagger-codegen.git
 */
using System;
using System.Linq;
using System.IO;
using System.Text;
using System.Collections;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.ComponentModel.DataAnnotations;
using System.Runtime.Serialization;
using Newtonsoft.Json;

namespace Javeriana.Convenios.Api.Models
{ 
    /// <summary>
    /// 
    /// </summary>
    [DataContract]
    public partial class ConveniosGETAllRsConvenios : IEquatable<ConveniosGETAllRsConvenios>
    { 
        /// <summary>
        /// Gets or Sets Direcciones
        /// </summary>
        [DataMember(Name="direcciones")]
        public string Direcciones { get; set; }

        /// <summary>
        /// Returns the string presentation of the object
        /// </summary>
        /// <returns>String presentation of the object</returns>
        public override string ToString()
        {
            var sb = new StringBuilder();
            sb.Append("class ConveniosGETAllRsConvenios {\n");
            sb.Append("  Direcciones: ").Append(Direcciones).Append("\n");
            sb.Append("}\n");
            return sb.ToString();
        }

        /// <summary>
        /// Returns the JSON string presentation of the object
        /// </summary>
        /// <returns>JSON string presentation of the object</returns>
        public string ToJson()
        {
            return JsonConvert.SerializeObject(this, Formatting.Indented);
        }

        /// <summary>
        /// Returns true if objects are equal
        /// </summary>
        /// <param name="obj">Object to be compared</param>
        /// <returns>Boolean</returns>
        public override bool Equals(object obj)
        {
            if (ReferenceEquals(null, obj)) return false;
            if (ReferenceEquals(this, obj)) return true;
            return obj.GetType() == GetType() && Equals((ConveniosGETAllRsConvenios)obj);
        }

        /// <summary>
        /// Returns true if ConveniosGETAllRsConvenios instances are equal
        /// </summary>
        /// <param name="other">Instance of ConveniosGETAllRsConvenios to be compared</param>
        /// <returns>Boolean</returns>
        public bool Equals(ConveniosGETAllRsConvenios other)
        {
            if (ReferenceEquals(null, other)) return false;
            if (ReferenceEquals(this, other)) return true;

            return 
                (
                    Direcciones == other.Direcciones ||
                    Direcciones != null &&
                    Direcciones.Equals(other.Direcciones)
                );
        }

        /// <summary>
        /// Gets the hash code
        /// </summary>
        /// <returns>Hash code</returns>
        public override int GetHashCode()
        {
            unchecked // Overflow is fine, just wrap
            {
                var hashCode = 41;
                // Suitable nullity checks etc, of course :)
                    if (Direcciones != null)
                    hashCode = hashCode * 59 + Direcciones.GetHashCode();
                return hashCode;
            }
        }

        #region Operators
        #pragma warning disable 1591

        public static bool operator ==(ConveniosGETAllRsConvenios left, ConveniosGETAllRsConvenios right)
        {
            return Equals(left, right);
        }

        public static bool operator !=(ConveniosGETAllRsConvenios left, ConveniosGETAllRsConvenios right)
        {
            return !Equals(left, right);
        }

        #pragma warning restore 1591
        #endregion Operators
    }
}
