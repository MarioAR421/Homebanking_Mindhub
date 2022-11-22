const {createApp} = Vue

createApp({

    data(){
        return{
          url :'/api/clients/current',
          accounts:null,
          client:null,
          accountOrigyn:"",
          accountDestiny:"",
          amount:"",
          description:"",
          destiny:"",

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
        this.name = result.data.firstName;
        this.accounts = result.data.account;
        this.client = result.data
      })
      },

      logOut(){
        axios
        .post('/api/logout').then(response => console.log('signed out!!!'))
        return window.location.href = "/web/index.html"
      },

      transfer(){
        transfern = "amount="+this.amount+"&description="+this.description+"&numberDestiny="+this.accountDestiny+"&numberOrigin="+this.accountOrigyn
        axios
        .post('/api/transactions',transfern)
        .then(response => {
            window.alert("Transferencia exitosa")
            location.reload()
        }).catch(error => {
            window.alert(error.response.data)
            
        })

      }


      

  

    },

    computed: {       
    }
}).mount('#app')