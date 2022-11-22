const {createApp} = Vue

createApp({

    data(){
        return{
          url :'http://localhost:8080/api/clients/current',
          accounts:null,
          accountDestiny:"",
          amount:"",
          loanType:"",
          payments:[],
          paymentsList: null,
          paymentsSelect: null,
          paymentPercentaje:null,

        }
    },
    created(){
      this.loadData(this.url)
      
    },
    methods: {

      loadData(url){
        axios
      .get(url)
      .then((result) => {
        this.accounts = result.data.account;
      })  
      },
      loadData2(){
        axios
        .get('http://localhost:8080/api/loans')
        .then((result) => {
          this.payments = result.data.filter(loan => loan.name == this.loanType)
          this.paymentsList = this.payments[0].payments
          if(this.loanType == "Hipotecario"){
            this.paymentPercentaje = 20
          }
          else if(this.loanType == "Automotriz"){
            this.paymentPercentaje = 10
          }

          else{
            this.paymentPercentaje = 5
          }
        }).catch(error => {conole.log(error)})
        
        },

        sendLoans(){
           loan = {
            "loanTypeId":this.loanType,
            "amount": this.amount,
            "payments": this.paymentsSelect,
            "numberAccount": this.accountDestiny
          }
          axios
          .post('http://localhost:8080/api/loans',loan )
          .then((result) => {
            window.alert(result.data)
            location.reload()
          }).catch((error) => {
            window.alert(error.request.response)
            console.log(error)
          })

        },
      

      

      logOut(){
        axios
        .post('/api/logout').then(response => console.log('signed out!!!'))
        return window.location.href = "/web/index.html"
      },

    },

    computed: {     
      
      

      

        
    }
}).mount('#app')