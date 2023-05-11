/**
 * 회원정보 관련 입력값 체크처리
 */
// 값을 담을 함수를 객체로 선언
var member = {
	tag_status: function( tag ){
		var name = tag.attr('name');
		if( name=='pw' )			return this.pw_status( tag.val() );
		else if( name=='pw_ck' )	return this.pw_ck_status( tag.val() );
		else if( name=='id' )		return this.id_status( tag.val() );
		else if( name=='email' )	return this.email_status( tag.val() );
	},
	
	//이메일 형식 지정	
	email_status: function( email ){
		var reg = /^[a-zA-Z0-9+-\_.]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/g;
		if( email=='' )					return this.common.empty;
		else if( reg.test(email) )		return this.email.valid;
		else                            return this.email.invalid;
	},
	email: {
		valid: { code:'valid', desc:'사용 가능한 이메일형식입니다' },
		invalid: { code:'invalid', desc:'사용할 수 없는 이메일형식입니다' },		
	},	
	
	id_status:function( id ){
		var reg = /[^a-z0-9]/g;
		if( id=='' ) 					return this.common.empty;
		else if( id.match(this.space) ) return this.common.space;
		else if( reg.test(id) )			return this.id.invalid;
		else if( id.length < 3 )    	return this.common.min;
		else if( id.length > 10 )    	return this.common.max;
		else                         	return this.id.valid;
	},
	
	id: {
		invalid : { code:'invalid', desc:'아이디는 영문소문자,숫자만 입력' },
		valid : { code:'valid', desc:'아이디 중복확인 하세요' },
		usable: { code:'valid', desc:'사용가능한 아이디입니다' },
		unUsable: { code:'invalid', desc:'이미 사용중인 아이디입니다' },
	},
	
	pw_status: function( pw ){
		var reg = /[^a-zA-Z0-9]/g, upper = /[A-Z]/g, lower=/[a-z]/g, digit=/[0-9]/g;
		if( pw=='' ) return this.common.empty;
		else if( pw.match(this.space) )	return this.common.space;
		else if( reg.test(pw) )			return this.pw.invalid;
		else if( pw.length<3 )			return this.common.min;
		else if( pw.length>10 )			return this.common.max;
		else if( !upper.test(pw) || !lower.test(pw) 
					|| !digit.test(pw) ) return this.pw.lack;
		else 							return this.pw.valid;
	},
	
//	전체 문자에 대해 스페이스바가 있는지 확인하는 코드
	space: /\s/g,
	
//	비밀번호 확인 일치/불일치 
	pw_ck_status: function( pw_ck ){
		if( pw_ck=='' )	return this.common.empty;
		else if( pw_ck==$('[name=pw]').val() )  return this.pw.equal;
		else 									return this.pw.notEqual;
	},
	
	pw: {
		equal: { code:'valid', desc:'비밀번호가 일치함' },	
		notEqual: { code:'invalid', desc:'비밀번호가 일치하지 않습니다' },	
		lack: { code:'invalid', desc:'영문 대/소문자, 숫자 모두 포함해야 함' },	
		valid: { code:'valid', desc:'사용가능한 비밀번호' },	
		invalid: { code:'invalid', desc:'영문대/소문자,숫자만 입력' },
	},
	
//	공통에 해당하는 데이터 값
	common: {
		empty: { code:'invalid', desc:'입력하세요' },
		space: { code:'invalid', desc:'공백없이 입력하세요' },
		min: { code:'invalid', desc:'3자 이상 입력하세요' }, 
		max: { code:'invalid', desc:'10자 이내 입력하세요' }, 
	} 
	
	
	
	
} 