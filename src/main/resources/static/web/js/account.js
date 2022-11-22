const {createApp} = Vue

createApp({

    data(){
        return{
          datos: null,  
          account:null,
          transactions:[],
          balance:null,
          nAccount:null,
          debito: null,
          credito:null,



        }
    },
    created(){
      this.loadData()
      
    },
    methods: {

      logOut(){
        axios.post('/api/logout').then(response => console.log('signed out!!!'))
        return window.location.href = "/web/index.html"
      },

      loadData(){
        const urlParams = new URLSearchParams(window.location.search);
            const Id = urlParams.get('id');
        
        axios
      .get(`/api/accounts/${Id}`)
      .then((result) => {
        this.datos = result;
        this.balance = result.data.balance
        this.nAccount = result.data.number
        this.transactions = result.data.transaction
        
      })
      },

      typeTransaction(tipo){
        if(tipo == "DEBITO"){
          this.debito = true
          this.credito = false
        }
        else{
          this.debito = false
          this.credito = true

        }
      },

      deleteAccount(){
        console.log(this.nAccount)

        axios
        .delete("/api/account/delete?number="+this.nAccount)
        .then(response => {
          window.alert("Eliminada con exito")
          return window.location.href = "/web/accounts.html"
        }).catch(err => {
          window.alert("No puede eliminar cuentas con saldo")
        })

      }


      


    },

    computed: {       
    }
}).mount('#app')

