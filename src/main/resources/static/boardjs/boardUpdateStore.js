const {defineStore}=Pinia
const useBoardUpdateStore=defineStore('board_update',{
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
		msg:'',
		no:0,
		vo:{}
	}),
	// 서버 연동
	actions:{
		async boardUpdate({
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
			const res=await axios.put('http://localhost:8080/board/update_ok_vue/',{
				name:this.name,
				subject:this.subject,
				content:this.content,
				pwd:this.pwd,
				no:this.no
			})
			if(res.data.msg==='yes')
			{
				location.href="/board/detail?no="+this.no
			}
			else
			{
				alert("글수정 실패")
				this.pwd=''
				pwdRef.focus()
			}
		},
		async boardUpdateData(no){
			const res=await axios.get('http://localhost:8080/board/update_vue/',{
				params:{
					no:no
				}
			})
			this.vo=res.data
			this.name=res.data.name
			this.subject=res.data.subject
			this.content=res.data.content
			this.no=res.data.no
		}
	}
})