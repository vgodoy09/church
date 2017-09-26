<%-- <%@page contentType="text/html" pageEncoding="ISO-8859-1"%> --%>
<!DOCTYPE html>
<html>
    <head>
<!--         <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"> -->
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="bower_components/bootstrap/dist/css/bootstrap.min.css" />
        <link rel="stylesheet" type="text/css" href="bower_components/metisMenu/dist/metisMenu.min.css" />
        <link rel="stylesheet" type="text/css" href="dist/css/sb-admin-2.css" />
        <link rel="stylesheet" type="text/css" href="bower_components/font-awesome/css/font-awesome.min.css" />
        <title>Acesso</title>
    </head>

    <body>
    	<div class="container">
	        <div class="row">
	        	<div class="col-md-4 col-md-offset-4">
<!-- 	        		<img alt="Assembléia de Deus Missionária" src="imagens/photoshop.gif" class="img-responsive"> -->
	        	</div>
	            <div class="col-md-4 col-md-offset-4">
    				
	                <div class="login-panel panel panel-default">
	                    <div class="panel-heading">
	                        <h3 class="panel-title">Please Enter</h3>
	                    </div>
	                    <div class="panel-body">
	                        <form role="form1" name="form1" action="ControlarAcesso" method="post">
	                            <fieldset>
	                                <div class="form-group">
	                                    <input class="form-control" placeholder="E-mail" name="email" type="email" value="${param['email']}" autofocus>
	                                </div>
	                                <div class="form-group">
	                                    <input class="form-control" placeholder="Password" name="password" type="password" value="${param['txtSenha']}">
	                                </div>
	                                <div class="checkbox">
	                                    <label>
	                                        <input name="remember" type="checkbox" value="Remember Me">Remember Me
	                                    </label>
	                                </div>
	                                <!-- Change this to a button or input when using this as a form -->
									<input type="submit" class="btn btn-lg btn-success btn-block" name="btnAcessar" value="Login" />
										
	                            </fieldset>
	                        </form>
	                    </div>
	                </div>
	            </div>
	        </div>
	    </div>
    	
    </body>
</html>
