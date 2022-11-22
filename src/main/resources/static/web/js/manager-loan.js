const {createApp} = Vue

createApp({

    data(){
        return{
            name:null,
            mount:null,
            payments:null,
        }
    },
    created(){
      
    },
    methods: {
        createLoan(){
            axios
            .post("http://localhost:8080/api/create/loan?name="+this.name+"&mount="+this.mount+"&payments="+this.payments)
            .then(response => {
                window.alert("Creado")
                
            }).catch(err => {
                window.alert("error")
            })
        }



    
      },

    computed: {       
    }
}).mount('#app')

