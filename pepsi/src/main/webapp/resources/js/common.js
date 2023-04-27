/**
 * 공통 함수
 */
function center(tag, bg){
	var width = Math.max( $(window).width() , $('body').prop('scrollWidth') );
	var height = Math.max( $(window).height() , $('body').prop('scrollHeight') );
	bg.css({'width': width, 'height': height });
	var left = ($(window).width() - tag.width())/2 + $(window).scrollLeft();
	var top = ($(window).height() - tag.height())/2 + $(window).scrollTop();
	tag.removeClass('center').css({'left': left, 'top':top, 'position': 'absolute'});
} 
function loading( is ){
	$('.loading').css('display', is ? 'block': 'none');
} 
 
function emptyCheck(){
	var ok = true;
	$('.chk').each(function(){
		if( $(this).val()=='' ){
			var item = $(this).attr('placeholder')
					  	? $(this).attr('placeholder') 
					  	: $(this).attr('title')	;
			alert(item + ' 입력하세요!');
			$(this).focus();
			ok = false;
			return ok;
		}		
	});
	return ok;
} 
 
$( function() {
	var today = new Date();
	var range = today.getFullYear()-100 + ':' + today.getFullYear();	
	
	if( $('.date').length > 0 ){
		$.datepicker.setDefaults({
			dateFormat: 'yy-mm-dd',
			
			dayNamesMin: [ '일', '월', '화', '수', '목', '금', '토' ],
			monthNamesShort: [ '1월', '2월', '3월', '4월', '5월', '6월'
						, '7월', '8월', '9월', '10월', '11월', '12월' ],
			 
			changeMonth: true,	
			changeYear: true,
			showMonthAfterYear: true,	
			maxDate: today,	
			yearRange: range,					
		});
	}

	//첨부파일 선택
    $('#attach-file').change(function(){
		console.log( this.files[0] );
		var attached = this.files[0];
		if( attached ){  //선택한 파일이 있는 경우
			$('#delete-file').css('display', 'inline');
			$('#file-name').text( attached.name );
			
			if( $('#preview').length==1 ){ //미리보기할 태그가 있는 경우
				//해당 이미지파일 정보를 읽어서 화면에 img 태그로 만든다
				if( isImage( attached.name ) ){
					$('#preview').html('<img>');
					var reader = new FileReader();
					reader.onload = function( e ){						
						$('#preview img').attr('src', e.target.result );
					}
					reader.readAsDataURL( attached );
				}else{
					$('#preview').empty();
				}
				
			}
			
		}else{
			initAttatch();
		}
	
	});
    
    $('#delete-file').click(function(){
		initAttatch();
		$('#file-name').text(''); //보여지는 파일명 없애기
		$('#attach-file').val(''); //실제 첨부된 파일정보 삭제
		//$('#preview').html('');
		//$('#preview img').remove();
		$('#preview').empty();
		$('#delete-file').css('display', 'none');	
	});

}); 


//여러개의 파일을 첨부하는 화면에서 사용할 처리
//동적으로 생성한 태그에 대한 이벤트 등록은 문서에 대해 처리해야한다
$(document).on('change', '.attach-file', function(){
	var attached = this.files[0];
	var _div = $(this).closest('div');
	
	if( attached ){
		if( $('[name=removed]').length==1 )removedFile(_div);
		
		//파일을 선택하면 첨부파일 태그를 복제해서 붙이기
		if( _div.children('.file-name').text()=='' )  copyFileTag();
		
		_div.children('.file-name').text( attached.name );
		_div.children('.delete-file').css('display', 'inline');
		
		if( isImage(attached.name) ){ //선택한 파일이 이미지인 경우 미리보기되게
			_div.children('.preview').html('<img>');
			var reader = new FileReader();
			reader.onload = function(e){
				_div.find('.preview img').attr('src', e.target.result);
			}
			reader.readAsDataURL( attached );				
		}else{
			_div.find('.preview img').remove();
		}
		
	}else{
		_div.remove();		
	}
	
}).on('click', '.delete-file', function(){
	console.log('delete')
	//선택한 삭제버튼에 해당하는 첨부파일태그 삭제
	var _div = $(this).closest('div');
	if( $('[name=removed]').length > 0 ) removedFile( _div );
	_div.remove();
	
})
;

//삭제한 파일id 관리하기
function removedFile( div ){
	
	var removed = $('[name=removed]').val();
	if( removed=='' ) removed = []; //[1,2] [1]
	else 
		removed = removed.indexOf(',')==-1 ? [removed] : removed.split(',');
	
	if(div.data('file')){
	removed.push(String(div.data('file')));  //DB에서 삭제할 정보를 배열변수에 추가
	removed = new Set(removed);		//중복 제거
	console.log( 'removed 변수 : ', removed);
	
		$('[name=removed]').val( Array.from( removed ) );
	}
	console.log( 'removed 태그 : ', $('[name=removed]').val() );
	
}

function copyFileTag(){
	var last = $("div.align").last();
	last.after( last.clone() );
	
	//복제한 파일태그 초기화
	last = $("div.align").last();
	last.find('.attach-file').val('');
	last.find('.file-name').text('');
	last.find('.delete-file').css('display', 'none');
}


function initAttatch(){
	$('#attach-file').val('');
	$('#file-name').text('');
	$('#preview').empty();
	$('#delete-file').css('display', 'none');
}

//이미지 파일인지 판단
function isImage( filename ){
	// abc.abc.txt, abc.png, abc.jpg, abc.PNG ...
	var ext = filename.substring( filename.lastIndexOf(".")+1 )
				.toLowerCase();
				
	var images = [ "jpg", "jpeg", "png", "bmp", "gif", "webp" ];
	return images.indexOf( ext ) == -1 ? false : true;			
}








