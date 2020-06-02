using Newtonsoft.Json.Converters;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Javeriana.Convenios.Api.Utils
{
    public class CustomDateTimeConverter : IsoDateTimeConverter
    {
        public CustomDateTimeConverter() {
            base.DateTimeFormat = "YYYY-MM-DDThh:mm:ss.SSS Z";
        }
    }
}
