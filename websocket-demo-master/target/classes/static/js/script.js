$(".toggle").on("click", function () {
  $(".container").stop().addClass("active");
});

$(".close").on("click", function () {
  $(".container").stop().removeClass("active");
});
//登录
$("#btn").click(function () {
  $.post("login?",$("#loginForm").serialize(),function(res){
    if (res.flag){
      console.log(res);
      location.href = "main";
    } else {
      console.log(res);
      $("#err_msg").html(res.message);
    }
  },"json");
});

//注册
$('#register').click(function(){
  $.post("register?",$('#registerForm').serialize(),function(res){
    if(res.flag){
      console.log(res);
      //location.href = "login";
      console.log('注册成功')
    }else{
      console.log(res);
      $("#err_msg").html(res.message);
    }

  },"json")
});


//http://localhost:8080/login.html