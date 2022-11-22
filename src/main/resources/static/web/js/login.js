const {createApp} = Vue

createApp({

    data(){
        return{
          email: null,
          password: null,

        }
    },
    created(){
    
    },

    methods: {


        logIn(){
            client= "email="+this.email+"&"+"password="+this.password
            if(this.email == null || this.password == null){
                window.alert("Faltan datos")
            }
            else{
            axios.post('/api/login',client)
           
            .then(response => {
                window.alert("Bienvenido a su HOME BANKING")
                console.log('signed in!!!');
                return window.location.href = "/web/accounts.html"

            }).catch(error => {
                return window.alert("Usuario o contraseña incorrecta")


            })
                
            
            }
            
        }

    },

    computed: {       
    }
}).mount('#app')

