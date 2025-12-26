const {defineStore}=Pinia
const useBoardInsertStore=defineStore('board_insert',{
	// 공통으로 사용하는 데이터 설정 => static
	// redux => state(store) = action dispatch() => reduce : vuex
	// pinia = tanStack-Query
	// ---------------------- NodeJS / TypeScript
	// 받는 변수(JSP), 서버에서 결과값
	state:()=>({
		name:'',
		subject:'',
		content:'',
		pwd:'',
		msg:''
	}),
	// 서버 연동
	actions:{
		// insert = 유효성 검사(NOT NULL)
		// 비동기 처리 => async
		async boardInsert({
			nameRef,subRef,contRef,pwdRef
		}){
			if(this.name==='')
			{
				nameRef.focus()
				return
			}
			if(this.subject==='')
			{
				subRef.focus()
				return
			}
			if(this.content==='')
			{
				contRef.focus()
				return
			}
			if(this.pwd==='')
			{
				pwdRef.focus()
				return
			}
			// => JSP
			const res=await axios.post('http://localhost:8080/board/insert_vue/',{
				name:this.name,
				subject:this.subject,
				content:this.content,
				pwd:this.pwd
			})
			if(res.data.msg==='yes')
			{
				location.href="/board/list"
			}
			else
			{
				alert("글쓰기 실패")
			}
		}
	}
})