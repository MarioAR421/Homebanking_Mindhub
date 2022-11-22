const {createApp} = Vue

createApp({

    data(){
        return{
            firstName:"",
            lastName:"",
            email:"",
            password:"",
        }
    },
    created(){
      
    },
    methods: {
        register(){
            client = "firstName="+this.firstName+"&"+"lastName="+this.lastName+"&email="+this.email+"&password="+this.password

            client2 = "email="+this.email+"&"+"password="+this.password

                axios.post('http://localhost:8080/api/clients',client)
                .then(response => {
                    window.alert("Bienvenido a Banco del sol")
                    console.log('registered!');
                    
                    return window.location.href = "/web/index.html"
    
                }).catch(error => {
                
                    return window.alert(error.response.data)
    
    
                })
            

            
                
        }

      
    },

    computed: {       
    }
}).mount('#app')