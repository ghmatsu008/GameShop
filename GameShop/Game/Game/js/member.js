
var pass = 'ログインパスワード';
var mail = 'メールアドレス';
var lNam = '姓';
var fNam = '名';
var lNam_k = '姓(ｶﾅ)';
var fNam_k = '名(ｶﾅ)';
var zip = '郵便番号';
var adrs = '住所';
var tel = 'TEL';

	function isReg(obj){
	    if( obj.value==""){
	    	obj.value = pass;
	    	obj.style.border='';
	    	obj.style.color = '#CCC';
	    }
	    if(obj.value !="")
	    	obj.style.border='';
	    
 	    if(obj.value.match(/[^0-9]/g)){
	        /* アラート表示 */
 	        alert (str.match(/[^0-9]/g)+'\n\n半角数値で入力して下さい');
 	        /* テキストボックスを空にする */
 	        obj.value="";
 	        return false;
 	    }
	}
	
	 function isR(obj){
		    if( obj.value==""){
		    	obj.style.border='';
		    	obj.style.color = '#CCC';
		    }if(obj.value !="")
		    	obj.style.border='';
		    	
		}
	
	    function isMail(obj){
		    if( obj.value==""){
		    	obj.value = mail;
		    	obj.style.border='';
		    	obj.style.color = '#CCC';
		    }if(obj.value !="")
		    	obj.style.border='';
		    	
		}
	
	 function isLnam(obj){
		    if( obj.value==""){
		    	obj.value = lNam;
		    	obj.style.border='';
		    	obj.style.color = '#CCC';
		    }if(obj.value !="")
		    	obj.style.border='';
		}
	 function isFnam(obj){
		    if( obj.value==""){
		    	obj.value = fNam;
		    	obj.style.border='';
		    	obj.style.color = '#CCC';
		    }if(obj.value !="")
		    	obj.style.border='';
		}
	 function isLnamK(obj){
		    if( obj.value==""){
		    	obj.value = lNam_k;
		    	obj.style.border='';
		    	obj.style.color = '#CCC';
		    }if(obj.value !="")
		    	obj.style.border='';
		}
	 function isFnamK(obj){
		    if( obj.value==""){
		    	obj.value = fNam_k;
		    	obj.style.border='';
		    	obj.style.color = '#CCC';
		    }if(obj.value !="")
		    	obj.style.border='';
		}
	 function isZip(obj){
		    if( obj.value==""){
		    	obj.value = zip;
		    	obj.style.border='';
		    	obj.style.color = '#CCC';
		    }if(obj.value !="")
		    	obj.style.border='';
		}
	 function isAdrs(obj){
		    if( obj.value==""){
		    	obj.value = adrs;
		    	obj.style.border='';
		    	obj.style.color = '#CCC';
		    }if(obj.value !="")
		    	obj.style.border='';
		}
	 function isTel(obj){
		    if( obj.value==""){
		    	obj.value = tel;
		    	obj.style.border='';
		    	obj.style.color = '#CCC';
		    }if(obj.value !="")
		    	obj.style.border='';
		}
	 
    function on(obj){
			
			obj.style.border= '#fff solid 2px';
        	obj.show.style.color = '#fff';
		}
// 	    if( obj.value==mail){
// 			obj.value='';
//         	obj.style.color = '#000000';
// 		}
	
	
	function onf(obj){
		if(obj.value == mail || obj.value==pass || obj.value==lNam||
			obj.value==fNam||
				obj.value==lNam_k||
					obj.value==fNam_k||
						obj.value==zip||
							obj.value==adrs||
								obj.value==tel ||
									obj.value==''){
			obj.value='';
			obj.style.border= '#FFD700 solid 1px';
        	obj.show.style.color = '#fff';
		}
// 	    if( obj.value==mail){
// 			obj.value='';
//         	obj.style.color = '#000000';
// 		}
	}
	

	$(document).ready(function() {

		$('#div1').hide();

		// 		$('#testform [name=nam]').val("");
		// 		$('#testform [name=asin]').val("");
		
		
		$("#sub").click(function() {
			var dat1 = $('#testform [name=LOGIN_ID]').val();
			var dat2 = $('#testform [name=LOGIN_PASSWORD]').val();
			var dat9 = $('#testform [name=LOGIN_PASSWORD_CONF]').val();
			var dat3 = $('#testform [name=LAST_NAME]').val();
			var dat4 = $('#testform [name=FIRST_NAME]').val();
			var dat5 = $('#testform [name=ZIP]').val();
			var dat6 = $('#testform [name=PREFECTURE]').val();
			var dat7 = $('#testform [name=ADDRESS]').val();
			var dat8 = $('#testform [name=TEL]').val();

			dat1 = jQuery.trim(dat1);
			dat2 = jQuery.trim(dat2);
			dat3 = jQuery.trim(dat3);
			dat4 = jQuery.trim(dat4);
			dat5 = jQuery.trim(dat5);
			dat6 = jQuery.trim(dat6);
			dat7 = jQuery.trim(dat7);
			dat8 = jQuery.trim(dat8);
			dat9 = jQuery.trim(dat9);

			
			alert("[" + dat1 + "] [" + dat2 + "]");
			
			

			if (dat1.length <= 0 || dat1==mail) {
				errorLid();
				return false;
			}
			if (dat2 != dat9 ) {
				error_pass();
				return false;
			}
			else if(dat9.length < 6 ){
				errorPassCon();
				return false;
				
			}
			if (dat3.length <= 0 || dat3 == lNam) {
				errorLnam();
				return false;
			}
			if (dat4.length <= 0 || dat4 == fNam) {
				errorFnam();
				return false;
			}
			if (dat5.length <= 0 || dat4 == zip) {
				errorZip();
				return false;
			}
			if (dat6 < 1  && dat6 > 47) {
				errorPref();
				return false;
			}
			if (dat7.length <= 0 || dat7==adrs) {
				errorAddress();
				return false;
			}
			if (dat8.length <= 0 || dat8==tel) {
				errorTel();
				return false;
			}
			if (dat9.length <= 6 || dat2.length <=6) {
				errorPassCon();
				return false;
			}
			
			
			if (!confirm('登録内容を保存します\nよろしいですか？')) {
				return false;
			}
			
			var data = {
				"dat1" : dat1,
				"dat2" : dat2,
				"dat3" : dat3,
				"dat4" : dat4,
				"dat5" : dat5,
				"dat6" : dat6,
				"dat7" : dat7,
				"dat8" : dat8,
				"dat9" : dat9
			};
			//var data = { "dat1" : dat1, "dat2" : dat2};

			var url = "InsertMember";
			$.ajax({
				type : 'POST',
				url : url,
				data : data,
				dataType : 'html',
				cache : false,
				success : function(data, textStatus) {
					$('#div1').html(data);
					$('#div1').show("slow");
				},
				error : function(xhr, textStatus, errorThrown) {
					alert('Error!' + textStatus + '' + errorThrown);

				}

			});
		});
		function errorLid() {
			$('.e1').html("<td style=\"color:RED\">未入力です</td>");
			$('.e1').show("slow");
		}
		function error_pass() {
			$('.e2').html("<td></td><td style=\"color:RED\">パスワードが一致しません</td>");
			$('.e2').show("slow");
		}
		function errorLnam() {
			$('.e3').html("<td></td><td style=\"color:RED\">未入力です</td>");
			$('.e3').show("slow");
		}
		function errorFnam() {
			$('.e4').html("<td></td><td style=\"color:RED\">未入力です</td>");
			$('.e4').show("slow");
		}
		function errorZip() {
			$('.e5').html("<td></td><td style=\"color:RED\">未入力です</td>");
			$('.e5').show("slow");
		}
		function errorPref() {
			$('.e6').html("<td></td><td style=\"color:RED\">未入力です</td>");
			$('.e6').show("slow");
		}
		function errorAddress() {
			$('.e7').html("<td></td><td style=\"color:RED\">未入力です</td>");
			$('.e7').show("slow");
		}
		function errorTel() {
			$('.e8').html("<td></td><td style=\"color:RED\">未入力です</td>");
			$('.e8').show("slow");
		}
		function errorPassCon() {
			$('.e9').html("<td></td><td style=\"color:RED\">6～10文字で入力してください</td>");
			$('.e9').show("slow");
		}
		

	});