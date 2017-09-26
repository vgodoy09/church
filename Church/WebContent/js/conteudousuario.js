// JavaScript Document

$(document).ready(function() {
	$('#conteudo').html('<iframe id="frameConteudo" name="frameConteudo" title="Conteudo da pagina" width="100%" height="800px" marginheight="0" marginwidth="5" frameborder="0" scrolling="Auto" src="../menu/inicio.jsf"></iframe>');

	$('#menuHome').click(function(){
		$('#conteudo').html('<iframe id="frameConteudo" name="frameConteudo" title="Conteudo da pagina" width="100%" height="800px" marginheight="0" marginwidth="5" frameborder="0" scrolling="Auto" src="../menu/inicio.jsf"></iframe>');
	});	
	
	$('#menuTrasacao').click(function(){
		$('#conteudo').html('<iframe id="frameConteudo" name="frameConteudo" title="Conteudo da pagina" width="100%" height="800px" marginheight="0" marginwidth="5" frameborder="0" scrolling="Auto" src="../menu/transacao.jsf"></iframe>');
	});			
	
	$('#menuAnalise').click(function(){
		$('#conteudo').html('<iframe id="frameConteudo" name="frameConteudo" title="Conteudo da pagina" width="100%" height="1043px" marginheight="0" marginwidth="5" frameborder="0" scrolling="Auto" src="../menu/analise.jsf"></iframe>');
	});		

	$('#menuSonho').click(function(){
		$('#conteudo').html('<iframe id="frameConteudo" name="frameConteudo" title="Conteudo da pagina" width="100%" height="800px" marginheight="0" marginwidth="5" frameborder="0" scrolling="Auto" src="../menu/sonho.jsf"></iframe>');
	});	

	$('#menuBensSeguros').click(function(){
		$('#conteudo').html('<iframe id="frameConteudo" name="frameConteudo" title="Conteudo da pagina" width="100%" height="800px" marginheight="0" marginwidth="5" frameborder="0" scrolling="Auto" src="../menu/benseseguros.jsf"></iframe>');
	});		
	
	$('#menuConfiguracoes').click(function(){
		$('#conteudo').html('<iframe id="frameConteudo" name="frameConteudo" title="Conteudo da pagina" width="100%" height="1043px" marginheight="0" marginwidth="5" frameborder="0" scrolling="Auto" src="../menu/configuracoes.jsf"></iframe>');
	});
	
//	$('#menuCategorias').click(function(){
//		$('#conteudoconfiguracao').html('<iframe id="frameConteudo" name="frameConteudo" title="Conteudo da pagina" width="100%" height="600px" marginheight="0" marginwidth="5" frameborder="0" scrolling="Auto" src="../menu/categorias.jsf"></iframe>');
//	});
});