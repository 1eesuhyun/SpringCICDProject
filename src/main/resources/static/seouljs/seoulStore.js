const {defineStore} = Pinia
const useSeoulStore=defineStore('seoul',{
	/*
		1. Pinia 동작
		   App 생성 => createApp()
		   Pinia 등록 => defineStore()
		   store 생성
		     | store= > 모든 Vue에서 사용하는 공통 변수 : static
			    | 변경시마다 = HTML에 적용
			  props => 불변
             
	*/
	state:()=>({
		list:[],
		curpage:1,
		totalpage:0,
		endPage:0,
		startPage:0,
		type:1,
		detail:{}
	}),
	// 기능 설정 => axios => BASE_URL
	actions:{
		// 목록 => 페이지 처리
		async seoulListData(type){
			this.type=type
			const res=await axios.get('http://localhost:9090/seoul/list_vue/',{
				params:{
					page:this.curpage,
					type:this.type
				}
			})
			console.log(res.data)
			this.list=res.data.list
			this.curpage=res.data.curpage
			this.endPage=res.data.endPage
			this.totalpage=res.data.totalpage
			this.startPage=res.data.startPage
			this.type=res.data.type
		},
		// 페이징
		pageChange(page){
			this.curpage=page
			this.seoulListData(this.type)
		},
		range(start,end){
			let arr=[]
			let len=end-start
			for(let i=0;i<=len;i++)
			{
				arr[i]=start // arr[i]=start
				start++
			}
			return arr
		}
		// 상세보기
	}
})