<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<title>Newsfeed</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="utf-8">
<link rel="icon" type="image/png" href="…">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.0/css/font-awesome.min.css">
<link href="https://fonts.googleapis.com/css?family=Anton" rel="stylesheet">
<link rel="stylesheet" href="<c:url value='resources/assets/css/test.css'/>">
</head>
<body>
<!--======Pixel Grid======-->
<div><img src="https://www.seanet.com/~owenmp/hvd-web/play-aids/grid-lg.gif" class="pixel-grid"/></div>
<div><i id="toggle-grid" class="fa fa-th"></i></div>
<!------------------------->
<!--======Push Menu======-->
<div class="container bar-cont">
    <div class="row">
        <div class="col-md-4 col-sm-6 push-bar">
          <div class="creator">
            <a href="https://codepen.io/Bahaa-Addin/" target="_blank"><i id="codepen" class="fa fa-codepen"></i></a>
          </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-2 arrow"></div>
    </div>
</div>
<!------------------------->
<!--========Cards========-->
<div class="container-fluid main-cont">
    <div class="row news-row">
        <div class="container-fluid col-md-12 col-sm-6 justify-content-center news-block">
            <div class="card-group">
              <div class="underlay">
                <div class="card">
                    <div class="card-img-top" style="background-image: url('https://i.imgur.com/wLMJQPH.png')"></div>
                    <div class="card-block" >
                    <h5 class="card-title" style="font-family: 'Anton', sans-serif">Canon Unveils New Lens<hr></h5>
                    <p class="card-text" >Canon will have a full slate of new and updated products to show attendees at this year’s NAB Show. The company has announced its new Compact-Servo 70-200mm telephoto zoom lens <a href="#"><u>Read More...</u></a></p>
                    <p class="card-text"><small class="text-muted">Last updated 3 mins ago</small></p>
                    </div>
                </div>
              </div>
              <div class="underlay">
               <div class="card">
                    <div class="card-img-top" style="background-image: url('https://i.imgur.com/tT6Bxay.png')"></div>
                    <div class="card-block" >
                      <h5 class="card-title" style="font-family: 'Anton', sans-serif">Sennheiser HD 598 Tech Specs<hr></h5>
                      <p class="card-text" >The brown Sennheiser HD 598 audiophile headphones have excellent, detailed hi-fi sound and a stylish design. They are comfortable to wear and offer versatility as well. These accessories feature a multi-dimensional soundstage <a href="#"><u>Read More...</u></a></p>
                      <p class="card-text"><small class="text-muted">Last updated 3 mins ago</small></p>
                    </div>
                  </div>
                </div>
                <div class="underlay">
                  <div class="card">
                    <div class="card-img-top" style="background-image: url('https://i.imgur.com/n6b2rib.png')"></div>
                    <div class="card-block" >
                      <h5 class="card-title" style="font-family: 'Anton', sans-serif">North Sea Wind Power Hub<hr></h5>
                      <p class="card-text" >The harnessing of energy has never been without projects of monolithic scale. From the Hoover Dam to the Three Gorges—the world's largest power station—engineers the world over have recognised that with size comes advantages <a href="#"><u>Read More...</u></a></p>
                      <p class="card-text"><small class="text-muted">Last updated 3 mins ago</small></p>
                    </div>
                  </div>
                </div>
                <div class="underlay">
                  <div class="card">
                    <div class="card-img-top" style="background-image: url('https://i.imgur.com/ssMsuUO.png')"></div>
                    <div class="card-block" >
                      <h5 class="card-title" style="font-family: 'Anton', sans-serif">Apple MacBook Retina displays<hr></h5>
                      <p class="card-text" >In 2015 we reported on an issue with MacBook Retina displays that were seeing problems with the anti-reflective coating wearing off, which came to be known as ‘Staingate.’ <a href="#"><u>Read More...</u></a></p>
                      <p class="card-text"><small class="text-muted">Last updated 3 mins ago</small></p>
                    </div>
                  </div>
                </div>
                <div class="underlay">
                  <div class="card">
                    <div class="card-img-top" style="background-image: url('https://i.imgur.com/yAFDnZ1.png')"></div>
                    <div class="card-block" >
                      <h5 class="card-title" style="font-family: 'Anton', sans-serif">Intel Dismisses 7700k Problems<hr></h5>
                      <p class="card-text" >Modern processors can run at temperatures ranging from 25 to 90 degrees, depending on configuration, cooling and workload. That said, when a CPU takes on a heavy load, that increase is gradual <a href="#"><u>Read More...</u></a></p>
                      <p class="card-text"><small class="text-muted">Last updated 3 mins ago</small></p>
                    </div>
                  </div>
                </div>
                <div class="underlay">
                  <div class="card">
                    <div class="card-img-top" style="background-image: url('https://i.imgur.com/5rgwfW6.png')"></div>
                    <div class="card-block" >
                      <h5 class="card-title" style="font-family: 'Anton', sans-serif">T3 Agenda: Beats By Dre<hr></h5>
                      <p class="card-text" >In the latest edition of the T3 Agenda, we prepare to box our ears with Beats By Dre's Anthony Joshua headphones, detail the pre-order incentives and special editions of Call of Duty WW2 <a href="#"><u>Read More...</u></a></p>
                      <p class="card-text"><small class="text-muted">Last updated 3 mins ago</small></p>
                    </div>
                  </div>
                </div>
                <div class="underlay">
                  <div class="card">
                    <div class="card-img-top" style="background-image: url('https://i.imgur.com/WaGs6zS.png')"></div>
                    <div class="card-block" >
                      <h5 class="card-title" style="font-family: 'Anton', sans-serif">Remembering &ldquo;Jobs&rdquo; 5th anniv<hr></h5>
                      <p class="card-text" >It has been 5 years since Steve Jobs passed away. Many publications write about how Apple Inc. has faired under Tim Cook's leadership post-Jobs <a href="#"><u>Read More...</u></a></p>
                      <p class="card-text"><small class="text-muted">Last updated 3 mins ago</small></p>
                    </div>
                  </div>
                </div>
                <div class="underlay">
                  <div class="card">
                    <div class="card-img-top" style="background-image: url('https://i.imgur.com/M8PGoML.png')"></div>
                    <div class="card-block" >
                      <h5 class="card-title" style="font-family: 'Anton', sans-serif">Pair iPod Touch with an AirPlay<hr></h5>
                      <p class="card-text" >Officially, in order to use AirPlay to stream music from your iOS device, you would need either an Apple TV, AirPort Express, or an AirPlay-enabled receiver or set of speakers <a href="#"><u>Read More...</u></a></p>
                      <p class="card-text"><small class="text-muted">Last updated 3 mins ago</small></p>
                    </div>
                  </div>
                </div>
                <div class="underlay">
                  <div class="card">
                    <div class="card-img-top" style="background-image: url('https://i.imgur.com/Hp8NxLI.png')"></div>
                    <div class="card-block" >
                      <h5 class="card-title" style="font-family: 'Anton', sans-serif">A Time-Travel Experiment Works<hr></h5>
                      <p class="card-text" >In a makeshift lab, a trio of brainiac students argue over their time-travel device. The skeptic on the team has her doubts—not that the invention will work <a href="#"><u>Read More...</u></a></p>
                      <p class="card-text"><small class="text-muted">Last updated 3 mins ago</small></p>
                    </div>
                  </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!------------------------>
<!--=======Scripts======-->
<script src="jquery-3.1.1.min.js"></script>
<script name="toggle-grid" type="text/javascript">
$(document).ready(function(){
  $(document).on("keypress", function(event) {
    // If 'alt + g' keys are pressed:
    if (event.which === 169){
        $('#toggle-grid').toggle();
     }
  });

  $('#toggle-grid').on("click"
  , function() {
      $('.pixel-grid').toggle();
      $('#toggle-grid').toggleClass('orange');
    });
});
var main = function () {
    $('.push-bar').on('click', function(event){
      if (!isClicked){
        event.preventDefault();
        $('.arrow').trigger('click');
        isClicked = true;
      }
    });
  
    $('.arrow').css({
      'animation': 'bounce 2s infinite'
    });
    $('.arrow').on("mouseenter", function(){
        $('.arrow').css({
                'animation': '',
                'transform': 'rotate(180deg)',
                'background-color': 'black'
           });
    });
     $('.arrow').on("mouseleave", function(){
        if (!isClicked){
            $('.arrow').css({
                    'transform': 'rotate(0deg)',
                    'background-color': 'black'
               });
        }
    });

    var isClicked = false;

    $('.arrow').on("click", function(){
        if (!isClicked){
            isClicked = true;
            $('.arrow').css({
                'transform': 'rotate(180deg)',
                'background-color': 'black',
           });

            $('.bar-cont').animate({
                top: "-15px"
            }, 300);
            $('.main-cont').animate({
                top: "0px"
            }, 300);
            // $('.news-block').css({'border': '0'});
            // $('.underlay').slideDown(1000);

        }
        else if (isClicked){
            isClicked = false;
            $('.arrow').css({
                'transform': 'rotate(0deg)',       'background-color': 'black'
           });

            $('.bar-cont').animate({
                top: "-215px"
            }, 300);
            $('.main-cont').animate({
                top: "-215px"
            }, 300);
        }
    console.log('isClicked= '+isClicked);
    });
  
    $('.card').on('mouseenter', function() {
      $(this).find('.card-text').slideDown(300);
    });
  
    $('.card').on('mouseleave', function(event) {
       $(this).find('.card-text').css({
         'display': 'none'
       });
     });
};

$(document).ready(main);

</script>
</body>
</html>