<script type="text/javascript">
    let inputData = {
        text:"",
        imgCount:3,
        imgQueue:[
            {path:"https://i.imgur.com/GCBVgXD.jpeg"},
            {path:"https://i.imgur.com/GCBVgXD.jpeg"},
            {path:"https://i.imgur.com/GCBVgXD.jpeg"},
        ]
    };

    function deleteImg(path){
        inputData.imgQueue = inputData.imgQueue.filter(img => img.path !== path);
        console.log(inputData);
        imageQueueUpdate();
    }

    //Display input images
    function imageQueueUpdate(){
        let container = document.getElementById('input-image');
        inputData.imgQueue.forEach(img => {   
        const image = document.createElement('div');
        image.innerHTML = `<img class="w-full h-full" src="${img.path}" alt="" />
                    <span class=" absolute -right-2 -top-1">
                        <svg width="22" height="22" viewBox="0 0 32 32" fill="none" xmlns="http://www.w3.org/2000/svg">
                            <path fill-rule="evenodd" clip-rule="evenodd" d="M20.2544 9.66272L15.7278 14.1893L11.2012 9.66272C10.7014 9.16298 9.89028 9.16298 9.39054 9.66272C8.89034 10.1629 8.8908 10.9736 9.39054 11.4734L13.9172 16L9.39054 20.5266C8.89034 21.0268 8.8908 21.8375 9.39054 22.3373C9.89028 22.837 10.701 22.8375 11.2012 22.3373L15.7278 17.8107L20.2544 22.3373C20.7542 22.837 21.5649 22.8375 22.0651 22.3373C22.5648 21.8375 22.5648 21.0264 22.0651 20.5266L17.5385 16L22.0651 11.4734C22.5648 10.9736 22.5648 10.1625 22.0651 9.66272C21.5654 9.16298 20.7542 9.16298 20.2544 9.66272ZM26.5917 26.8639C20.5917 32.864 10.864 32.864 4.86391 26.8639C-1.13614 20.8639 -1.13614 11.1361 4.86391 5.13609C10.8635 -0.863501 20.5917 -0.863954 26.5917 5.13609C32.5918 11.1361 32.5913 20.8643 26.5917 26.8639Z" fill="white"/>
                        </svg>
                    </span>`;
        container?.appendChild(image);
        });
    }

	// Function to handle file upload
	const handleFileUpload = (files) => {
		if (files.length === 0) {
			console.error('No file selected');
			return;
		}
		const filePath = URL.createObjectURL(files[0]);
		uploadImage(filePath);
	};

	// Function to upload the image
	const uploadImage = async (filePath) => {
		try {
			const file = await fetchFile(filePath);
			const formData = new FormData();
			formData.append('file', file, filePath.split('/').pop());

			const response = await fetch('192.168.133.129:8080/api/upload', {
				method: 'POST',
				body: formData
			});

			if (!response.ok) {
				throw new Error('Failed to upload image');
			}

			const data = await response.text();
			console.log('Image uploaded successfully:', data);
		} catch (error) {
			console.error('Error uploading image:', error.message);
		}
	};

	// Function to fetch file asynchronously
	const fetchFile = async (filePath) => {
		try {
			const response = await fetch(filePath);
			if (!response.ok) {
				throw new Error('Failed to fetch file');
			}
			const blob = await response.blob();
			return new File([blob], filePath.split('/').pop());
		} catch (error) {
			console.error('Error fetching file:', error.message);
			throw error;
		}
	};

	// // Add event listener to file input element
	// const fileInput = document.getElementById('fileInput');
	// fileInput.addEventListener('change', (event) => {
	// 	handleFileUpload(event.target.files);
	// });

    imageQueueUpdate();

	import PicsIcon			from '$lib/icon/pics.svg?component';
	import LocationIcon	from '$lib/icon/location-pin.svg?component';
</script>

<a href="#" class="mx-auto w-11 h-11 xl:w-auto flex items-center justify-center bg-blue-400 hover:bg-blue-500 py-3 rounded-full text-white font-bold font-sm transition duration-350 ease-in-out mb-10" onclick="my_modal_1.showModal()">
    <svg fill="currentColor" viewBox="0 0 24 24" class="block xl:hidden h-6 w-6">
      <path d="M8.8 7.2H5.6V3.9c0-.4-.3-.8-.8-.8s-.7.4-.7.8v3.3H.8c-.4 0-.8.3-.8.8s.3.8.8.8h3.3v3.3c0 .4.3.8.8.8s.8-.3.8-.8V8.7H9c.4 0 .8-.3.8-.8s-.5-.7-1-.7zm15-4.9v-.1h-.1c-.1 0-9.2 1.2-14.4 11.7-3.8 7.6-3.6 9.9-3.3 9.9.3.1 3.4-6.5 6.7-9.2 5.2-1.1 6.6-3.6 6.6-3.6s-1.5.2-2.1.2c-.8 0-1.4-.2-1.7-.3 1.3-1.2 2.4-1.5 3.5-1.7.9-.2 1.8-.4 3-1.2 2.2-1.6 1.9-5.5 1.8-5.7z"></path>
    </svg>
    <span class="hidden xl:block font-bold text-md">Post</span>
  </a>
<!-- https://daisyui.com/components/modal/ -->


<!-- New element that can be added on top -->
<!-- <div class="new-element w-5/12 bg-gray-800 rounded-2xl p-2"> -->



<dialog id="my_modal_1" class="modal w-100">
	<!-- Press ESC key or click outside to close -->
	<form method="dialog" class="modal-backdrop">
        <button>close</button>
    </form>

    <div class="modal-box max-w-none max-h-none w-5/12 p-4 pt-12 bg-white dark:bg-gray-900 text-black dark:text-white">
        <form method="dialog" class="text-right">
            <button class="btn text-lg btn-sm btn-circle btn-ghost absolute right-2 top-2">✕</button>
        </form>

        <div class="flex gap-2">
            <div class="flex-shrink-0 w-10 h-10">
                <img class="w-full h-full rounded-full" src="https://i.imgur.com/GCBVgXD.jpeg" alt="" />
            </div>

            <div class="bg-gray-300 dark:bg-gray-700 w-full rounded-xl">
                <form class="w-full">
                    <textarea class="w-full h-52 text-black dark:text-white text-xl bg-gray-300 dark:bg-gray-700 rounded-xl p-2" placeholder="What's happening?"></textarea>
                </form>
                <div class="flex">
                    {#each inputData.imgQueue as img} 
                    <div class="relative flex-shrink-0 w-12 h-12 m-2">  
                        <img class="w-full h-full" src={img.path} alt="" />
                        <span class="absolute -right-2 -top-1 cursor-pointer" onclick="deleteImg('{img.path}')">
                            <svg width="22" height="22" viewBox="0 0 32 32" fill="none" xmlns="http://www.w3.org/2000/svg">
                                <path fill-rule="evenodd" clip-rule="evenodd" d="M20.2544 9.66272L15.7278 14.1893L11.2012 9.66272C10.7014 9.16298 9.89028 9.16298 9.39054 9.66272C8.89034 10.1629 8.8908 10.9736 9.39054 11.4734L13.9172 16L9.39054 20.5266C8.89034 21.0268 8.8908 21.8375 9.39054 22.3373C9.89028 22.837 10.701 22.8375 11.2012 22.3373L15.7278 17.8107L20.2544 22.3373C20.7542 22.837 21.5649 22.8375 22.0651 22.3373C22.5648 21.8375 22.5648 21.0264 22.0651 20.5266L17.5385 16L22.0651 11.4734C22.5648 10.9736 22.5648 10.1625 22.0651 9.66272C21.5654 9.16298 20.7542 9.16298 20.2544 9.66272ZM26.5917 26.8639C20.5917 32.864 10.864 32.864 4.86391 26.8639C-1.13614 20.8639 -1.13614 11.1361 4.86391 5.13609C10.8635 -0.863501 20.5917 -0.863954 26.5917 5.13609C32.5918 11.1361 32.5913 20.8643 26.5917 26.8639Z" fill="white"/>
                            </svg>
                        </span>
                    </div>
                    {/each}
                </div>
            </div>
        </div>

        <div class="flex pt-4 pl-4 items-center justify-between text-black dark:text-white">
            <div class="flex gap-4">
                <PicsIcon width=24 height=24 fill="currentColor"/>
                <LocationIcon width=24 height=24 fill="currentColor"/>			
            </div>
            <div class="flex">
                
                <div class="relative flex-shrink-0 w-10/12 px-2 text-black dark:text-white absolute -right-2">
                    <button class="w-40 p-2 bg-blue-500 hover:bg-opacity-40 rounded-full font-bold border border-gray-700 ">
                        Share </button>
                </div>
            </div>
        </div>

    </div>

</dialog>