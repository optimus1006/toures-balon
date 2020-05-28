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
    /// Respuesta a la actualizacion de un convenio
    /// </summary>
    [DataContract]
    public partial class ConveniosPCTRs : IEquatable<ConveniosPCTRs>
    { 
        /// <summary>
        /// Gets or Sets Convenio
        /// </summary>
        [Required]
        [DataMember(Name="convenio")]
        public Convenio Convenio { get; set; }

        /// <summary>
        /// Returns the string presentation of the object
        /// </summary>
        /// <returns>String presentation of the object</returns>
        public override string ToString()
        {
            var sb = new StringBuilder();
            sb.Append("class ConveniosPCTRs {\n");
            sb.Append("  Convenio: ").Append(Convenio).Append("\n");
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
            return obj.GetType() == GetType() && Equals((ConveniosPCTRs)obj);
        }

        /// <summary>
        /// Returns true if ConveniosPCTRs instances are equal
        /// </summary>
        /// <param name="other">Instance of ConveniosPCTRs to be compared</param>
        /// <returns>Boolean</returns>
        public bool Equals(ConveniosPCTRs other)
        {
            if (ReferenceEquals(null, other)) return false;
            if (ReferenceEquals(this, other)) return true;

            return 
                (
                    Convenio == other.Convenio ||
                    Convenio != null &&
                    Convenio.Equals(other.Convenio)
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
                    if (Convenio != null)
                    hashCode = hashCode * 59 + Convenio.GetHashCode();
                return hashCode;
            }
        }

        #region Operators
        #pragma warning disable 1591

        public static bool operator ==(ConveniosPCTRs left, ConveniosPCTRs right)
        {
            return Equals(left, right);
        }

        public static bool operator !=(ConveniosPCTRs left, ConveniosPCTRs right)
        {
            return !Equals(left, right);
        }

        #pragma warning restore 1591
        #endregion Operators
    }
}
