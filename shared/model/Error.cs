using System;
using System.Collections.Generic;
using Microsoft.AspNetCore.Http;

namespace Insurance.Model
{
    public class Error<T>
    {
        public string path { get; set; }
        public List<T> details { get; set;}

        public Error(PathString path, List<T> details) {
            this.path = path;
            this.details = details;
        }
    }
}
