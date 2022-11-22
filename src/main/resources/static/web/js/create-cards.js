const {createApp} = Vue

createApp({

    data(){
        return{
          type: null,
          color: null,
          cards: null,
          cardsCredit: null,
          cardsDebit: null,
          url :'/api/clients/current',

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
            this.cards = result.data.card
            this.cardsCredit = this.cards.filter((card) => card.type =="CREDIT")
            this.cardsDebit = this.cards.filter((card) => card.type =="DEBIT")
            console.log(this.cards)
            console.log(this.cardsCredit)
            console.log(this.cardsDebit)
          })
          },


        createCard(){
            card = "cardType=" + this.type + "&cardColor=" + this.color
            if(this.type == null || this.color == null){
              window.alert("Elija tipo y color")

            }
            else{
            axios.post("/api/clients/current/cards ",card)
            .then(responce => 
                console.log("creado"))
                return window.location.href = "/web/cards.html"
            }
        }

    },

    computed: {       
    }
}).mount('#app')
