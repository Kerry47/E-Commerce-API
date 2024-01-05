# E-commerece - API
But as the young kids call it EasyShop, or an online shop!
# The rundown
So in this wonderful API, I worked on a (as a backend developer) on a existing website. Now there is a lready a version already made/done but we all know nothing really stops at the verison 1, so its time for updates so will be working on version 2.
*Disclamer * This was not made from scratch, as code was given to us to individually work on/improve * Disclamer *.
# The nitty gritty 
In this section will get into the things that I worked on/did throughout the project. Now there are different Phases, I only got up to phase 3 which was optional but with help, I got through it!
# Phase 1
Within this Phase I worked on two classes to complete the task, the classes were CategoriesController and the MySqlCategoryDao. Within this phase we had Implement code for each method and add the proper annotation for each controller.

![image](https://github.com/Kerry47/E-Commerce-API/assets/147070013/26c395ff-db52-4166-9727-6e5b595b1af1)
# Phase 2
Fix bugs or Bug fix's (whichever you prefer), so as you can see in this phase I worked on fixing and cleaning up bugs that was in the code. There were two bugs to fix, here is the first one: 
  so the problem within this code was that the users have reported the product search functionallity, is returning incorrect information, so before hand in the body it was productDao.create(UpdateId, product) which was the issue, so I fixed it by putting productDao.update(UpdateId, product)
  as before it was creating new products rather then just updating it.
  
![image](https://github.com/Kerry47/E-Commerce-API/assets/147070013/d4cf68b8-c7ad-4812-b39e-7871940693be)

Bug #2 
Within this bug the maxPrice basically wasnt being used as it wasnt initialized or called upon, so too fix that I went and called upon the class and filled out the required information

![image](https://github.com/Kerry47/E-Commerce-API/assets/147070013/47184466-d102-403f-ba2b-50d2cb3a419f)

# Phase 3
Now phase 3 was a little challenging for me but I did have assitance with it and now I have a better understanding of it, so in this phase we were implementing a new feature which was the ability to  return shopping cart, add products to shopping cart, clear shopping cart, and upadte the quantity of a product with the cart
which is also GET, POST, PUT, DELETE.
Now it should only be available to the user who is logged in.

![image](https://github.com/Kerry47/E-Commerce-API/assets/147070013/ae331aa4-6490-4a99-a744-1579e7773106)

![image](https://github.com/Kerry47/E-Commerce-API/assets/147070013/041bf3da-c02e-40e5-8ca1-8fa66936b2c3)

# Testing
Now of course we had to make sure that these functions/methods work, so we used postman to ensure that everything was functional did what is required!

![image](https://github.com/Kerry47/E-Commerce-API/assets/147070013/0545415c-33d0-40fc-8f16-ac8c98690f87)

# The final product
Here is a look of the webiste with the new iplementations!

![image](https://github.com/Kerry47/E-Commerce-API/assets/147070013/03df32d6-0a50-419b-a921-0950547f5c27)

