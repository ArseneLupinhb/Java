
// 1 成功
(function ($) { 
$.support.placeholder = ('placeholder' in document.createElement('input')); })(jQuery); 
//fix for IE7 and IE8
 $(function () { 
 	if (!$.support.placeholder) { 
 	$("[placeholder]").focus(function () { 
 		if ($(this).val() == $(this).attr("placeholder"))
 		 $(this).val(""); }).blur(function () { 
 		 	if ($(this).val() == "")
 		 	 $(this).val($(this).attr("placeholder")); }).blur(); 
 	$("[placeholder]").parents("form").submit(function () { 
 			$(this).find('[placeholder]').each(function() {
 			 if ($(this).val() == $(this).attr("placeholder")) {
 			  $(this).val(""); } }); }); } });

// 3失败



 // 2成功
//  $(function(){
//     if(!!navigator.userAgent.match(/MSIE 8.0/)){
//             $('input[placeholder], textarea[placeholder]').each(function() {
//                 var input = $(this);
//                 if (input.val() === '' && input.attr("placeholder") !== ''){   
//                     input.addClass("placeholder").val(input.attr('placeholder'));
//                 }

//                 input.focus(function() {
//                     if (input.val() === input.attr('placeholder')) {
//                         input.val('');
//                     }
//                 });

//                 input.blur(function() {
//                     if (input.val() === '' || input.val() === input.attr('placeholder')) {
//                         input.val(input.attr('placeholder'));
//                     }
//                 });
//             });
//     }
// });