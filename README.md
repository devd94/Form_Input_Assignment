FORM INPUT ASSIGNMENT
----------------------

The assignment is in Kotlin and using XML Layouts.

It includes a simple form with inputs for name, age, date of birth and address. I have assumed the first 3 to be required fields and the date of birth is selected through a date picker.

I have added necessary checks for the required fields and added an additional check to make sure the age and date of birth entered do not have any obvious mismatches. Ideally, we should have only one input field for the date of birth only and we can compute the age in our code to store it. This ensures a better user experience as well.

The data is saved in RoomDb using a single User table. 

I have used MVVM architecture with Hilt dependency injection to structure the application.

I have also added a few test cases in the FormActivityTest class.
