using System;
using System.Linq;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Logging;
using Newtonsoft.Json;

using Insurance.Model;

namespace Insurance.Repositories
{
    class InMemoryCustomerRepository : IRepository<Customer>
    {
        private static List<Customer> customers = new List<Customer>();

        public IEnumerable<Customer> getAll()
        {
            return customers.OrderBy(c => c.id);
        }

        public Customer create(string requestBody)
        {
            Customer data = parseRequestBody(requestBody);

            if (valdiateCustomer(data))
            {
                // Temporary in-memory solution for generating CustomerID (not scalable)
                data.id = customers.LastOrDefault() != null
                    ? customers.LastOrDefault().id + 1
                    : 1;

                customers.Add(data);

                return data;
            }
            else
            {
                throw new BadRequestException("Unknown bad request.");
            }
        }
        public Customer getById(string id)
        {
            int parsedId;

            return Int32.TryParse(id, out parsedId)
                ? customers.FirstOrDefault(c => c.id == parsedId)
                : throw new BadRequestException("Invalid id, id has to be a number.");
        }
        public Customer updateById(string id, string requestBody)
        {
            Customer customer = getById(id);

            Customer data = parseRequestBody(requestBody);

            if (valdiateCustomer(data))
            {
                data.id = customer.id;
                customers.Remove(customer);
                customers.Add(data);

                return data;
            }
            else
            {
                throw new BadRequestException("Unknown bad request.");
            }
        }
        public Customer deleteById(string id)
        {
            int parsedId;

            Customer customer = Int32.TryParse(id, out parsedId)
                ? customers.FirstOrDefault(c => c.id == parsedId)
                : throw new BadRequestException("Invalid id, id has to be a number.");

            customers.Remove(customer);

            return customer;
        }

        private Customer parseRequestBody(string requestBody)
        {
            try
            {
                return JsonConvert.DeserializeObject<Customer>(requestBody);
            }
            catch (JsonSerializationException)
            {
                throw new JsonSerializationException("Invalid Json.");
            }
        }

        private bool valdiateCustomer(Customer newCustomer)
        {
            if (newCustomer != null)
            {
                // In-memory data validation
                var results = new List<ValidationResult>();

                var isValid = Validator.TryValidateObject(
                    newCustomer,
                    new ValidationContext(newCustomer, null, null),
                    results,
                    true
                );

                return isValid
                    ? isValid
                    : throw new InvalidFieldException(results);
            }
            else
            {
                throw new BadRequestException("Request body is empty.");
            }
        }
    }
}