const app = Vue.createApp({
    data(){
        return{
        email:"",
        password:"",
        registerFirstName:"",
        registerLastName: "",
        registerEmail: "",
        registerPassword: "",
        //disable: true,
        message:"",
        picked:"",
        showPassword: true,

        //errores
        error: false,
        error_msg: "",
        }
    },
    created(){
    
    },
    methods:{
        logIn(email, password){
            axios.post('/api/login',`email=${email}&password=${password}`,
            {headers:{'content-type':'application/x-www-form-urlencoded'}})
            .then(res => {                
                if(res.status === 200){                   
                   window.location ='web/account.html'
               }
            })           
            .catch(error=> {
                this.error = true;
                this.error_msg = "Algo salió mal, vuelve a ingresar tu usuario y contraseña"
                })
        },
        registrationUser(){
            axios.post('/api/users',`firstName=${this.registerFirstName}&lastName=${this.registerLastName}&email=${this.registerEmail}&password=${this.registerPassword}`,{headers:{'content-type':'application/x-www-form-urlencoded'}})            
            .then(res=>{                
                console.log("Registro exitoso")                
                this.logIn(this.registerEmail, this.registerPassword) 
                this.registerFirstName=""
                this.registerLastName=""
                this.registerEmail=""            
                this.registerPassword=""
            })
            .catch(error =>{               
                this.error = true;                     
                this.error_msg = error.response.data   
                console.log(error.response)         
                console.log(error.response.data)
            })          
        },
        capitalizeName(name){
            name = name.toLowerCase()
            return name[0].toUpperCase() + name.slice(1)
        },
        lowerCaseMail(mail){
            return mail.toLowerCase()
        },       
        registerPrev(){
            let next = this.$refs.form1
            let next1 = this.$refs.form2
            let next2 = this.$refs.btn
            let next3 =  this.$refs.color1
            let next4 =  this.$refs.color2
            next.style.left = "800px";
            next1.style.left= "0px";
            next2.style.left= "125px";
            next3.style.color= "black";
            next4.style.color= "#fff";
        },
        loginPrev(){
            let next = this.$refs.form1
            let next1 = this.$refs.form2
            let next2 = this.$refs.btn
            let next3 =  this.$refs.color1
            let next4 =  this.$refs.color2
            next.style.left = "0px";
            next1.style.left= "-800px";
            next2.style.left= "0px";
            next3.style.color= "#fff";
            next4.style.color= "black";
        } ,
        showPasswords(){
            this.showPassword = !this.showPassword
        }, 
    },
    
})
app.mount("#app")