<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <title>Grobot &gt;&gt; Minions</title>
    <!-- Bootstrap -->
    <link href="/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  </head>
  <body>
    <div class="container">
        <div class="navbar navbar-inverse">
          <div class="navbar-inner">
            <div class="container">
         
              <!-- .btn-navbar is used as the toggle for collapsed navbar content -->
              <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
              </a>
         
              <!-- Be sure to leave the brand out there if you want it shown -->
              <a class="brand" href="/home">Grobot</a>
         
              <div class="nav-collapse">
                <!-- .nav, .navbar-search, .navbar-form, etc -->
                <ul class="nav">
                  <li><a href="/home">Home</a></li>
                  <li class="divider-verital"></li>
                  <li class="active"><a href="/ping/index.do">Minions</a></li>
                  <li class="divider-verital"></li>
                  <li><a href="/taks">Tasks</a></li>
                  <li class="divider-verital"></li>
                  <li><a href="/docs">Documentation</a></li>
                </ul>
                <form class="navbar-form pull-right">
                  <input type="text" name="username" class="input-small" placeholder="Username">
                  <input type="password" name="password" class="input-small" placeholder="Password">
                  <button type="Submit" class="btn btn-warning btn-red">Login</button>
                </form>
              </div>
         
            </div>
          </div>
        </div>
    </div>
    <script src="http://code.jquery.com/jquery-latest.js"></script>
    <script src="/bootstrap/js/bootstrap.min.js"></script>
  </body>
</html>