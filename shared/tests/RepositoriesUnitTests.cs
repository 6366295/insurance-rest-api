using Xunit;
using Newtonsoft.Json;

using Insurance.Model;
using Insurance.Repositories;

namespace Insurance.UnitTests
{
    public class RepositoriesUnitTests
    {
        private IRepository<Customer> customerRepository;
        private static string validJson = "{\"surname\" : \"Trieu\",\"initials\" : \"M\",\"telephoneNumber\" : \"+31611111111\",\"email\" : \"mike@gmail.com\",\"zipCode\" : \"1111 AA\",\"iban\" : \"NL05INGB1234123400\",\"dateOfBirth\" : \"sdfsdf\",\"nationality\" : \"sdfsdf\",\"bsn\": \"sdfsdf\",\"houseNumber\": \"sdfsdf\",\"streetName\": \"sdfsdf\",\"city\": \"sdfsdf\"}";
        
        public RepositoriesUnitTests() {
            customerRepository = new InMemoryCustomerRepository();
        }

        [Fact]
        public void DeleteCustomer()
        {
            Customer addNewCustomer = customerRepository.create(validJson);

            customerRepository.deleteById("1");

            Customer customer = customerRepository.getById("1");

            Assert.True(customer == null);
        }

        [Fact]
        public void ValidCustomerRequest()
        {
            Customer addFirstNewCustomer = customerRepository.create(validJson);

            Customer correctCustomer = customerRepository.getById("1");

            Assert.True(correctCustomer.surname == "Trieu");
            Assert.True(correctCustomer.initials == "M");

            customerRepository.deleteById("1");
        }

        [Fact]
        public void InvalidIdRequest()
        {
            Assert.Throws<BadRequestException>(() => customerRepository.getById("rofl"));
        }

        [Fact]
        public void ValidCustomerCreation()
        {
            Customer addFirstNewCustomer = customerRepository.create(validJson);
            Customer addSecondNewCustomer = customerRepository.create(validJson);

            Assert.True(addFirstNewCustomer.id == 1);
            Assert.True(addSecondNewCustomer.id == 2);

            customerRepository.deleteById("1");
            customerRepository.deleteById("2");
        }

        [Fact]
        public void InvalidFieldZipcode()
        {
            string invalidZipCodeJson = "{\"surname\" : \"Trieu\",\"initials\" : \"M\",\"telephoneNumber\" : \"+31611111111\",\"email\" : \"mike@gmail.com\",\"zipCode\" : \"1111 AAfgh\",\"iban\" : \"NL05INGB1234123400\",\"dateOfBirth\" : \"sdfsdf\",\"nationality\" : \"sdfsdf\",\"bsn\": \"sdfsdf\",\"houseNumber\": \"sdfsdf\",\"streetName\": \"sdfsdf\",\"city\": \"sdfsdf\"}";

            Assert.Throws<InvalidFieldException>(() => customerRepository.create(invalidZipCodeJson));
        }

        [Fact]
        public void InvalidFieldTelephoneNumber()
        {
            string invalidTelephoneNumberJson = "{\"surname\" : \"Trieu\",\"initials\" : \"M\",\"telephoneNumber\" : \"sdfklsdjflksdf\",\"email\" : \"mike@gmail.com\",\"zipCode\" : \"1111 AA\",\"iban\" : \"NL05INGB1234123400\",\"dateOfBirth\" : \"sdfsdf\",\"nationality\" : \"sdfsdf\",\"bsn\": \"sdfsdf\",\"houseNumber\": \"sdfsdf\",\"streetName\": \"sdfsdf\",\"city\": \"sdfsdf\"}";

            Assert.Throws<InvalidFieldException>(() => customerRepository.create(invalidTelephoneNumberJson));
        }

        [Fact]
        public void InvalidFieldInitials()
        {
            string invalidInitialsJson = "{\"surname\" : \"Trieu\",\"initials\" : \"MM\",\"telephoneNumber\" : \"+31611111111\",\"email\" : \"mike@gmail.com\",\"zipCode\" : \"1111 AA\",\"iban\" : \"NL05INGB1234123400\",\"dateOfBirth\" : \"sdfsdf\",\"nationality\" : \"sdfsdf\",\"bsn\": \"sdfsdf\",\"houseNumber\": \"sdfsdf\",\"streetName\": \"sdfsdf\",\"city\": \"sdfsdf\"}";

            Assert.Throws<InvalidFieldException>(() => customerRepository.create(invalidInitialsJson));
        }



        [Fact]
        public void InvalidJson()
        {
            string invalidJson = "{";

            Assert.Throws<JsonSerializationException>(() => customerRepository.create(invalidJson));
        }

        [Fact]
        public void ValidJsonEmptyFields()
        {
            string emptyFields = "{}";

            Assert.Throws<InvalidFieldException>(() => customerRepository.create(emptyFields));
        }

        [Fact]
        public void InvalidBody()
        {
            string emptyBody = "";

            Assert.Throws<BadRequestException>(() => customerRepository.create(emptyBody));  
        }
        
        // [Fact]
        // public void Test1()
        // {
        //     Customer customer = new Customer();

        //     customer.surname = "Trieu";
        //     customer.initials = "M";
        //     customer.telephoneNumber = "+31611111111";
        //     customer.email = "mike@gmail.com";
        //     customer.zipCode = "1111 AA";
        //     customer.iban = "NL05INGB1234123400";
        //     customer.dateOfBirth = "sdfsdf";
        //     customer.nationality = "sdfsdf";
        //     customer.bsn = "sdfsdf";
        //     customer.houseNumber = "sdfsdf";
        //     customer.streetName = "sdfsdf";
        //     customer.city = "sdfsdf";

        //     // string jsonString = "{\"surname\" : \"Trieu\",\"initials\" : \"M\",\"telephoneNumber\" : \"+31611111111\",\"email\" : \"mike@gmail.com\",\"zipCode\" : \"1111 AA\",\"iban\" : \"NL05INGB1234123400\",\"dateOfBirth\" : \"sdfsdf\",\"nationality\" : \"sdfsdf\",\"bsn\": \"sdfsdf\",\"houseNumber\": \"sdfsdf\",\"streetName\": \"sdfsdf\",\"city\": \"sdfsdf\"}";
        //     string jsonString = "{}";
        //     // var result = customerRepository.create(jsonString);
        //     // Assert.True(result.Equals(customer));
        //     Assert.Throws<InvalidFieldException>(() => customerRepository.create(jsonString));

        //     jsonString = "";
        //     // var result = customerRepository.create(jsonString);
        //     // Assert.True(result.Equals(customer));
        //     Assert.Throws<BadRequestException>(() => customerRepository.create(jsonString));
            
        // }
    }
}
