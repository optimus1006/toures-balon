using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.Threading.Tasks;

namespace Javeriana.Convenios.Api.Exceptions
{
    public class ConvenioYaExisteException : Exception
    {
        public ConvenioYaExisteException() {
        }

        public ConvenioYaExisteException(string message) : base(message) {
        }

        public ConvenioYaExisteException(string message, Exception innerException) : base(message, innerException) {
        }

        protected ConvenioYaExisteException(SerializationInfo info, StreamingContext context) : base(info, context) {
        }
    }
}
