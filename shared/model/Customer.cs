using System;
using System.ComponentModel.DataAnnotations;

namespace Insurance.Model
{
    public class Customer
    {
        public int id { get; set; }

        [Required]
        public String dateOfBirth { get; set; }

        [Required]
        [RegularExpression(@"[a-zA-Z]+", ErrorMessage = "The surname field only allows alphabet characters (upper- and lower-case).")]
        public String surname { get; set; }

        [Required]
        [RegularExpression(@"^[A-Z]([\s][A-Z])*$", ErrorMessage = "The initials field has to be formatted as: A, A A, A A A, ...")]
        public String initials { get; set; }

        [Required]
        public String nationality { get; set; }

        [Required]
        public String bsn { get; set; }

        [Required]
        [RegularExpression(@"^[a-zA-Z]{2}[0-9]{2}[a-zA-Z0-9]{4}[0-9]{7}([a-zA-Z0-9]?){0,16}$", ErrorMessage = "The iban field is not valid.")]
        public String iban { get; set; }

        [Required]
        [RegularExpression(@"^[1-9][0-9]{3}[\s]?[A-Za-z]{2}$", ErrorMessage = "The zipCode field is not valid.")]
        public String zipCode { get; set; }

        [Required]
        public String houseNumber { get; set; }

        [Required]
        public String streetName { get; set; }

        [Required]
        public String city { get; set; }

        [Required]
        [Phone]
        public String telephoneNumber { get; set; }
        
        [Required]
        [EmailAddress]
        public String email { get; set; }
    }
}
