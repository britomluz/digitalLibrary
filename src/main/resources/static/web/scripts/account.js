const app = Vue.createApp({
    data(){
        return{
            books:[],
            users:[],
            book:[],
            user:[],
            booksYear:[],
            bookCategory:"",
            booksBestseller:[],
            booksFav:[],
            bookModal:[],

            //filters
            filterAuthor:"",
            filterTitle:"",
            filterDay:'',
            filterMonth:'',
            filterYear:'',
            

            //add book
            bookTitle:"",
            bookAuthor:"",
            bookEditorial:"",
            bookCategory:"",
            bookPrice:"",
            bookDate:"",            
            bookImage: "https://res.cloudinary.com/luz-brito/image/upload/v1638657510/Acquerello/imgDefault_qbhg4k.jpg",
            bookDescription:"",

            //upload image books to cloudinary
            CLOUDINARY_URL: "",
            CLOUDINARY_UPLOAD_PRESET: "",
            urlImg: "",
            imagePreviewDos: "",
            imageUploaderDos: "",

            //edit book
            errorEdit:false,
            errorEditBook:"",
            showbtn:false,

            bookTitleEdit:"",
            bookAuthorEdit:"",
            bookEditorialEdit:"",
            bookCategoryEdit:"",
            bookPriceEdit:"",
            bookDateEdit:"",            
            bookImageEdit: "",
            bookDescriptionEdit:"",

        }

    },

    created(){
        this.dataBooks()
        this.dataUsers()
        this.dataCurrentUser()
        this.loadDataBook()
       
    },
    methods:{
      dataBooks(){
        axios.get("/api/books")
        .then(res => {
          this.books = res.data.sort((a,b) => parseInt(a.id - b.id))
          this.booksYear = this.books.slice(10,16)
          this.booksBestseller = this.books.filter(book => book.bestSeller == 'YES')
          this.booksFav = this.books.filter(book => book.type == 'FAV')
         // console.log(this.booksBestseller)
          //this.accounts = res.data.accounts.sort((a,b) => parseInt(a.id - b.id))
          
        
      })
        .catch(err => err.message)  
      },  
      showBook(e) {
        let id = e.target.parentElement.id
        //console.log(id)
        window.location.href = `./book-details.html?id=${id}`
  
      },      
      loadDataBook(){
        const urlParam = new URLSearchParams(window.location.search);
        const id = urlParam.get('id');        
        
        axios.get(`/api/book/${id}`)
              .then(res => {                
                this.book = res.data          
                //console.log(this.book)
                //this.show_btn()
            })
              .catch(err => err.message)
        },
      dataUsers(){
        axios.get("/api/users")
        .then(res => {
          this.users = res.data
         // console.log(this.users)
         // this.accounts = res.data.accounts.sort((a,b) => parseInt(a.id - b.id))
         
        
      })
        .catch(err => err.message)  
      }, 
      dataCurrentUser(){
        axios.get("/api/users/current")
        .then(res => {
          this.user = res.data
        //  console.log(this.user)
        // this.accounts = res.data.accounts.sort((a,b) => parseInt(a.id - b.id))
        
        
      })
        .catch(err => err.message)  
      },
        showModal(id){
          console.log(id)
          this.bookModal = this.books.filter(book => book.id == id)          
          console.log(this.bookModal)                  
        },
        addBestSeller(e){
          e.preventDefault()
          console.log(e.target.parentElement.id)
          let bookId = e.target.parentElement.id
          axios.patch("/api/books/bestsellers/edit",`bookId=${bookId}`, {headers:{'content-type':'application/x-www-form-urlencoded'}}) 
            .then(res => {
                console.log("Libro agregado a BestSeller")      
                console.log(res.data)
                window.location.reload(); 
                                   
            })
            .catch(error=>{
                console.log("No se pudo añadir el libro")
                swal("Ha ocurrido un error", "No se puede agregar más de 10 libros a Best Sellers");   
            })
        },
        addLibrary(e){
          
          console.log(e.target.firstChild.value)
          let bookId = e.target.firstChild.value
          axios.patch("/api/books/addLibrary/edit",`bookId=${bookId}`, {headers:{'content-type':'application/x-www-form-urlencoded'}}) 
            .then(res => {
                console.log("Libro agregado a la biblioteca")     
                
                window.location.reload();                 

            })
            .catch(error=>{
                console.log("No se pudo añadir el libro")
                   
            })
        },
        uploadImageBook(event) {
          this.imagePreviewDos = this.$refs.imagePreviewDos
          this.imageUploaderDos = this.$refs.imageUploaderDos
          this.CLOUDINARY_URL = 'https://api.cloudinary.com/v1_1/luz-brito/image/upload'
          this.CLOUDINARY_UPLOAD_PRESET = 'qda9s173'
    
          console.log(event.target.files[0])
          const fileImg = event.target.files[0]
    
          const formData = new FormData;
    
          formData.append('file', fileImg)
          formData.append('upload_preset', this.CLOUDINARY_UPLOAD_PRESET)
    
          axios.post(this.CLOUDINARY_URL, formData, { headers: { 'Content-Type': 'multipart/form-data' } })
            .then(res => {
              console.log("Imagen cargada!")
              console.log(res)
              this.imagePreviewDos.src = res.data.secure_url
    
              this.bookImage = this.imagePreviewDos.src
              this.bookImageEdit = this.imagePreviewDos.src
    
    
              //console.log(this.products_img)
    
            })
    
        },        
        addBook(e){
          e.preventDefault()
          axios.post('/api/books',{
              // id: this.categoryId,
              titulo: this.bookTitle,
              autor: this.bookAuthor,
              editorial: this.bookEditorial,
              categoria: this.bookCategory,
              precio: this.bookPrice,
              fechaLanzamiento: this.bookDate,
              portada: this.bookImage,
              resenia: this.bookDescription,   
          })
          .then((res) => {
              console.log("Product created");
              swal({
                title: "Bien hecho!",
                text: "Libro agregado con éxito",
                icon: "success",
                button: "OK!",
              })
              .then(res=>{
                console.log("exitoso")
                window.location.reload()
              })
          })
          .catch(err => {
            console.log(err)
            console.log("error")
          })
      },
      show_btn() {    
        this.bookTitle= this.book.titulo 
        this.bookAuthor=this.book.autor
        this.bookEditorial=this.book.editorial
        this.bookCategory=this.book.categoria
        this.bookPrice=this.book.precio
        this.bookDate=this.book.fechaLanzamiento            
        this.bookImage= this.book.portada
        this.bookDescription=this.book.resenia
        this.showbtn = !this.showbtn;
      },
      editBook(){        
        console.log(this.$refs.btn.id)
        let bookId = this.$refs.btn.id
             
        const date = new Date(this.bookDate).toISOString().slice(0,10);
        
        axios.patch(`/api/book/edit/${bookId}`,{            
            titulo: this.bookTitle,
            autor: this.bookAuthor,
            editorial: this.bookEditorial,
            categoria: this.bookCategory,
            precio: this.bookPrice,
            fechaLanzamiento: date,
            portada: this.bookImage,
            resenia: this.bookDescription,   
        })
        .then((res) => {
            console.log("Product created");
            swal({
              title: "Bien hecho!",
              text: "Libro editado con éxito",
              icon: "success",
              button: "OK!",
            })
            .then(res=>{
              console.log("exitoso")
              window.location.reload()
            })
        })
        .catch(err => {
          this.errorEdit=true
          this.errorEditBook=err.response.data
        })
    },
      deleteBook(e){
        let bookId = e.target.parentElement.id
        swal({
          title: "Estas seguro?",
          text: "Una vez borrado no se puede recuperar la información",
          icon: "warning",
          buttons: true,
          dangerMode: true,
        })
        .then((willDelete) => {
          if (willDelete) {

            axios.delete(`/api/books/delete/${bookId}`)
          .then((response) => {
            console.log(response.data)  
            swal("El libro fue borrado con éxito", {
              icon: "success",
            })
            .then(res => window.location.reload()) 
          })
          .catch((err) => console.log(err));            
          } else {
            swal("El libro se encuentra a salvo!");
          }
        });
      },      
      logout(){
        axios.post('/api/logout')
        .then( res => {
            window.location ='index.html'
        })
        .catch(err =>{
            console.log(err)
            this.errorAdd=true
            this.errorAddBook=err.response.data
        })
    },            

    },
    computed:{
      booksByCategory(){

        return this.books.filter(book => book.categoria.toLowerCase() == this.bookCategory || this.bookCategory == "")
                         .filter(book => book.titulo.toLowerCase().match(this.filterTitle.toLowerCase()))
                         .filter(book => book.titulo.toLowerCase().match(this.filterAuthor.toLowerCase()))
                         .filter(book => book.fechaLanzamiento.slice(0,2).match(this.filterDay))
                         .filter(book => book.fechaLanzamiento.slice(3,5).match(this.filterMonth))
                         .filter(book => book.fechaLanzamiento.slice(6,10).match(this.filterYear)) 
      },
      
    }

});

app.mount("#app")