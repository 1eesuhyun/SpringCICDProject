const {defineStore} = Pinia
const useBusanStore=defineStore('busan',{
	state:()=>({
		list:[],
		curpage:1,
		totalpage:0,
		endPage:0,
		startPage:0,
		type:1,
		detail:{}
	}),
	actions:{
		async busanListData(type){
			this.type=type
			const res=await axios.get('http://localhost:9090/busan/list_vue/',{
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
			this.busanListData(this.type)
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