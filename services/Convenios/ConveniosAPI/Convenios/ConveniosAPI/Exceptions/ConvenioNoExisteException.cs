using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.Threading.Tasks;

namespace Javeriana.Convenios.Api.Exceptions
{
    public class ConvenioNoExisteException : Exception
    {
        public ConvenioNoExisteException() {
        }

        public ConvenioNoExisteException(string message) : base(message) {
        }

        public ConvenioNoExisteException(string message, Exception innerException) : base(message, innerException) {
        }

        protected ConvenioNoExisteException(SerializationInfo info, StreamingContext context) : base(info, context) {
        }
    }
}
