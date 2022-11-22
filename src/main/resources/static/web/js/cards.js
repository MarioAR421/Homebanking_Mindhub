const {createApp} = Vue

createApp({

    data(){
        return{
          name : "",
          url :'/api/clients/current',
          cards:null,
          cardsCredit: null,
          cardsDebit: null,
          cardGold: null,
          cardSilver: null,
          cardTitanium: null,
          time:null,

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
      })
      },
      logOut(){
        axios
        .post('/api/logout').then(response => console.log('signed out!!!'))
        return window.location.href = "/web/index.html"
      },

      filterDate(date) {
        return date.slice(2, 7);
      },

      filterDate2(date) {
        this.time = date.slice(2, 4);
        console.log(this.time);
      },

      colorCard(color){
        if(color == "GOLD"){
          this.cardGold = true
          this.cardSilver = false
          this.cardTitanium = false
        }
        else if(color == "SILVER"){
          this.cardGold = false
          this.cardSilver = true
          this.cardTitanium = false

        }
        else {
          this.cardGold = false
          this.cardSilver = false
          this.cardTitanium = true

        }
      },

      deleteCard(number){
        console.log(number)
        card = "number="+number

        axios
        .delete("/api/cards/delete?"+card)
        .then(response =>{
          window.alert("Eliminada con exito")
          location.reload()}
          )
          
          .catch(error =>
            window.alert(error))
      },
      


      

      

    },

    computed: {       
    }
}).mount('#app')



