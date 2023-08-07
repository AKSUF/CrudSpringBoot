 $(document).ready(function() {
	 
	 //for regidstration
	 
        $("#registrationForm").on("submit", function(event) {
            event.preventDefault(); // Prevent the default form submission

            var registrationData = {
                firstName: $("input[name='firstName']").val(),
                lastName: $("input[name='lastName']").val(),
                email: $("input[name='email']").val(),
                password: $("input[name='password']").val()
            };

            $.ajax({
                url: "http://localhost:8285/api/v1/auth/register",
                type: "POST",
                dataType: "json",
                contentType: "application/json",
                data: JSON.stringify(registrationData),
                success: function(response) {
                    // Registration successful, you can handle the response here
                    console.log("Registration successful:", response);
                },
                error: function(error) {
                    // Registration failed, you can handle the error here
                    console.error("Registration failed:", error);
                }
            });
        });
        
        
        
    });
