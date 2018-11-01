<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="aboutUs.css">
<title>About Us</title>

<%
if(session.getAttribute("username")==null) {
%>
<jsp:include page="header.jsp"></jsp:include>
<%
} else {
%>
<jsp:include page="afterLoginHeader.jsp"></jsp:include>
<%
}%>


</head>
<div class="container">
    <div class="box">

        <h1>About Us</h1>

        <p>&emsp;Since 1995, Yamuda Rawmak online vehicle renting system specializes in renting a vehicle 
        	by your desire, and customize especially to your needs. With our
            passion and creativity, we bring a fresh approach to every vehicle.</p>

        <p>&emsp;We provide a multitude of services, and you will be dealing with yourself to
            produce an unforgettable journey that will be tailored to you and your vision.
            During a complementary initial planning, you have the opportunity to look through
            your services charges, learn more about our services and ask any questions you may
            have.</p>

        <p>&emsp;We value the relationship that we form with our clients, and take pride in our ability
            to make your vision a reality. Yamuda Rawmak online vehicle renting system is here to ensure 
            that every last detail is executed smoothly, so that you can sit back or drive your own,
            relax, and enjoy the day.</p>

    </div>

    <div class="img-box1">
        <img src="homeImages/aboutUs1.jpg"/>
    </div>

    <div class="img-box2">
        <img src="homeImages/aboutUs2.png"/>
    </div>
    
    <div class="img-box2">
        <img src="homeImages/aboutUs3.jpg"/>
    </div>
    
    <div class="img-box2">
        <img src="homeImages/aboutUs4.jpg"/>
    </div>


    <div class="comments-box1">
        <p>"Yamuda Rawmak was wonderful! It is so user friendly,
            easy to work with and will be having the all functionalities to make your
            desired destination come true. I can honestly say that my journey plan
            would not have ran as smoothly without it."</p>
        <p>~ Camille</p>
    </div>

    <div class="comments-box2">
        <p>"It was obvious that this whole website, and all it's services, really
            love the work that I can do and really had my best interest in mind.
            By the end of it, I couldn't believed that how easy to rent a vehicle with good quality."</p>
        <p>~ Katie</p>
    </div>

    <div class="comments-box3">
        <p>"Yamuda Rawmak online vehicle renting was AMAZING!! This site
            went above and beyond to help customize a package that fit our needs
            and our budget. Not enough good things to say about Yamuda Rawmak! Thank You!"</p>
        <p>~ Amy</p>
    </div>

    <div class="bottom"></div>

</div>
</body>
<%@include file="footer.jsp" %>
</html>