using System;
using System.IO;
using System.Linq;
using System.Threading.Tasks;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;

using Microsoft.AspNetCore.Mvc;
using Microsoft.Azure.WebJobs;
using Microsoft.Azure.WebJobs.Extensions.Http;
using Microsoft.AspNetCore.Http;
using Microsoft.Extensions.Logging;
using Newtonsoft.Json;

using Insurance.Logger;
using Insurance.Model;
using Insurance.Repositories;

namespace Insurance.Function
{
    public static class HttpTriggerCustomers
    {
        // private static IRepository<Customer> customerRepository = new InMemoryCustomerRepository<Customer>();
        private static ILoggerDecorator<Customer> customerRepository = new LoggerDecorator<Customer>(new InMemoryCustomerRepository());

        [FunctionName("GetCustomers")]
        public static IActionResult GetCustomers(
            [HttpTrigger(AuthorizationLevel.Anonymous, "get", Route = "customers")] HttpRequest request,
            ILogger log)
        {
            var customers = customerRepository.getAll(log);

            return new OkObjectResult(customers);
        }

        [FunctionName("CreateCustomer")]
        public static async Task<IActionResult> CreateCustomer(
            [HttpTrigger(AuthorizationLevel.Anonymous, "post", Route = "customers")] HttpRequest request,
            ILogger log)
        {
            string requestBody = await new StreamReader(request.Body).ReadToEndAsync();

            try
            {
                var customer = customerRepository.create(requestBody, log);

                return new CreatedResult($"customers/{customer.id}", customer);
            }
            catch (BadRequestException ex)
            {
                return new BadRequestObjectResult(new Error<string>(request.Path, new List<string>() { ex.Message }));
            }
            catch (InvalidFieldException ex)
            {
                return new BadRequestObjectResult(new Error<ValidationResult>(request.Path, (List<ValidationResult>)ex.invalidObject));
            }
            catch (JsonSerializationException ex)
            {
                return new BadRequestObjectResult(new Error<string>(request.Path, new List<string>() { ex.Message }));
            }
        }

        [FunctionName("GetCustomersById")]
        public static IActionResult GetCustomersById(
            [HttpTrigger(AuthorizationLevel.Anonymous, "get", Route = "customers/{id}")] HttpRequest request,
            ILogger log,
            string id)
        {
            try
            {
                var customer = customerRepository.getById(id, log);

                return customer != null
                    ? (ActionResult)new OkObjectResult(customer)
                    : new NotFoundObjectResult(new Error<string>(request.Path, new List<string>() { $"Customer does not exist." }));
            }
            catch (BadRequestException ex)
            {
                return new BadRequestObjectResult(new Error<string>(request.Path, new List<string>() { ex.Message }));
            }
        }

        [FunctionName("UpdateCustomersById")]
        public static async Task<IActionResult> UpdateCustomersById(
            [HttpTrigger(AuthorizationLevel.Anonymous, "put", Route = "customers/{id}")] HttpRequest request,
            ILogger log,
            string id)
        {
            string requestBody = await new StreamReader(request.Body).ReadToEndAsync();

            try
            {
                var customer = customerRepository.updateById(id, requestBody, log);

                return customer != null
                    ? (ActionResult)new OkObjectResult(customer)
                    : new NotFoundObjectResult(new Error<string>(request.Path, new List<string>() { $"Customer does not exist." }));
            }
            catch (BadRequestException ex)
            {
                return new BadRequestObjectResult(new Error<string>(request.Path, new List<string>() { ex.Message }));
            }
            catch (InvalidFieldException ex)
            {
                return new BadRequestObjectResult(new Error<ValidationResult>(request.Path, (List<ValidationResult>)ex.invalidObject));
            }
            catch (JsonSerializationException ex)
            {
                return new BadRequestObjectResult(new Error<string>(request.Path, new List<string>() { ex.Message }));
            }
        }

        [FunctionName("DeleteCustomersById")]
        public static IActionResult DeleteCustomersById(
            [HttpTrigger(AuthorizationLevel.Anonymous, "delete", Route = "customers/{id}")] HttpRequest request,
            ILogger log,
            string id)
        {
            try
            {
                var customer = customerRepository.deleteById(id, log);

                return customer != null
                    ? (ActionResult)new OkObjectResult(customer)
                    : new NotFoundObjectResult(new Error<string>(request.Path, new List<string>() { $"Customer does not exist." }));
            }
            catch (BadRequestException ex)
            {
                return new BadRequestObjectResult(new Error<string>(request.Path, new List<string>() { ex.Message }));
            }
        }
    }
}
