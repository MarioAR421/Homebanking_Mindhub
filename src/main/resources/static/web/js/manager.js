const {createApp} = Vue

createApp({

    data(){
        return{
            datos: null,  
            clients : null,
            name : "",
            lastname: "",
            email : "",
            url :'http://localhost:8080/api/clients',
            id: null,
            json:null
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
        this.datos = result;
        this.json = result.data;
        this.clients = (this.datos.data);
      })

      },

      addClient(){
        if(this.name.length > 0 && this.lastname.length > 0 && this.email.length > 0){
        let client = {
          firstName : this.name,
          lastName : this.lastname,
          email : this.email          
        }
        this.postClient(client)
          
      }
      else{
        alert("Faltan datos");
      }
      },

      postClient(dato){
        axios
        .post(this.url, dato)
        .then(() => {
          this.loadData(this.url)
        })

      },

    },

    computed: {       
    }
}).mount('#app')

