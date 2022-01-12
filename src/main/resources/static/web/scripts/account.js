const app = Vue.createApp({
    data(){
        return{
            books:[],
            books2:[],
            users:[],
            userbooks:[],
            book:[],
            user:[],
            booksYear:[],
            bookCategory:"",
            booksBestseller:[],
            booksFav:[],
            bookModal:[],
            bookLib:[],

            

            //filters and pagination   
            totalPages:"",
            totalBooks:"",            
            actualPage:0,
            size:"",
            sort:"",            
            direction:"",
            byTitulo:"",
            byAutor:"",
            byEditorial:'',
            byCategoria:'',
            byPrecio:500,         
            

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

        }

    },

    created(){
        this.dataBooks()
        this.loadBooks()
        this.dataUsers()
        this.dataCurrentUser()
        this.loadDataBook()
       
    },
    methods:{      
      dataBooks(page){
        
        this.page = page-1        
        this.size = 12    

        console.log(this.page)
        
        axios.get(`/api/books?page=${this.page}&size=${this.size}&sort=${this.sort}${this.direction}&filter%5Bcategoria%5D=${this.byCategoria}&filter%5Btitulo%5D=${this.byTitulo}&filter%5Bautor%5D=${this.byAutor}&filter%5Beditorial%5D=${this.byEditorial}&filter%5Bprecio%5D=${this.byPrecio}`)
        .then(res => {
          this.books = res.data.content
          this.actualPage = res.data.numberPage
          //console.log(this.page)
          this.totalBooks = res.data.totalBooks
          this.totalPages = res.data.totalPages
          this.size = res.data.size       
          
        
      })
        .catch(err => err.message)  
      },  
      getPrevPage(){            
        if(this.actualPage-1 > 1){
            this.actualPage --
        }
       this.dataBooks(this.actualPage)
      },
      getNextPage(){       
        console.log()     
        console.log(this.actualPage)
          if(this.actualPage < this.totalPages-1){
              this.actualPage ++
          }
      this.dataBooks(this.actualPage+1)
      },
      isActive(numPage){
        
        return numPage == this.actualPage+1 ? "active" : ""
       
    },
      loadBooks(){

        axios.get("/api/books")
        .then(res => {
          this.books2  = res.data.content.sort((a,b) => parseInt(a.id - b.id))         
          this.booksYear = this.books2.slice(2,7)
          this.booksBestseller = this.books2.filter(book => book.bestSeller == true)
          this.booksFav = this.books2.filter(book => book.type == 'FAV')
          
        
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
        axios.get(`/api/books/${id}`)
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
         
      })
        .catch(err => err.message)  
      }, 
      dataCurrentUser(){
        axios.get("/api/users/current")
        .then(res => {
          this.user = res.data
          this.userbooks = this.user.userbooks

          this.containsBook()

         // console.log(this.userbooks)
         // console.log(this.user)
        
      })
        .catch(err => err.message)  
      },
      containsBook(){
        console.log(this.userbooks)

        this.bookLib = this.userbooks.map( tb => tb.titulo)

        console.log(this.bookLib)
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
          axios.patch(`/api/books/bestseller?bookId=${bookId}`, {headers:{'content-type':'application/x-www-form-urlencoded'}}) 
            .then(res => {
                console.log("Libro agregado a BestSeller")      
                console.log(res.data)
                window.location.reload(); 
                                   
            })
            .catch(error=>{
                console.log("No se pudo añadir el libro")
                swal("No se puede agregar más de 10 libros a Best Sellers.", "Debes eliminar un libro de Best Sellers para agregar otro.");   
            })
        },
        addLibrary(e){
          
          console.log(e.target.firstChild.value)
          let bookId = e.target.firstChild.value
          axios.post("/api/books/userbooks",`bookId=${bookId}`, {headers:{'content-type':'application/x-www-form-urlencoded'}}) 
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
        
        axios.put(`/api/books/${bookId}`,{            
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

            axios.delete(`/api/books/${bookId}`)
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
            console.log(err.response.data)
            //this.errorAdd=true
            //this.errorAddBook=err.response.data
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