using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Javeriana.Convenios.Api.HealthChecks
{
    public class HealthCheckResponse
    {
        public string status { get; set; }
        public IEnumerable<CheckInfo> Checks { get; set; }
        public TimeSpan Duration { get; set; }
    }
}
