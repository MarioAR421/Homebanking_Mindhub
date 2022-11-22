const {createApp} = Vue

createApp({

    data(){
        return{
          name : "",
          url :'/api/clients/current',
          accounts:null,
          loans:null,
          client:null,
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
        this.loans = result.data.loan
        this.client = result.data
      })
      },

      logOut(){
        axios
        .post('/api/logout').then(response => console.log('signed out!!!'))
        return window.location.href = "/web/index.html"
      },

      newAccount(type){
        axios
        .post('/api/clients/current/accounts?type=AHORRO')
        .then(response => console.log('nueva cuenta creada'))
        location.reload()
        

      },

      newAccount2(){
        axios
        .post('/api/clients/current/accounts?type=CORRIENTE')
        .then(response => console.log('nueva cuenta creada'))
        location.reload()
        

      },
      filterDate(date) {
        return date.slice(0, 10);
      }

      

  

    },

    computed: {       
    }
}).mount('#app')

