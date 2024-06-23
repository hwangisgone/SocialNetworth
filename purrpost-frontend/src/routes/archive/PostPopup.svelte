<script type="text/javascript">


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

	import PicsIcon			from '$lib/icon/pics.svg?component';
	import LocationIcon	from '$lib/icon/location-pin.svg?component';
</script>


<button class="w-full p-2 bg-blue-500 hover:bg-opacity-40 rounded-full font-bold border border-gray-700 "
	onclick="my_modal_1.showModal()">
	New Post
</button>
<!-- https://daisyui.com/components/modal/ -->


<!-- New element that can be added on top -->
<!-- <div class="new-element w-5/12 bg-gray-800 rounded-2xl p-2"> -->



<dialog id="my_modal_1" class="modal w-100">
	<!-- Press ESC key or click outside to close -->
	<form method="dialog" class="modal-backdrop">
    <button>close</button>
  </form>

<div class="modal-box max-w-none max-h-none w-5/12 p-4 pt-12">
	<form method="dialog" class="text-right">
    <button class="btn text-lg btn-sm btn-circle btn-ghost absolute right-2 top-2">✕</button>
  </form>

	<div class="flex gap-2">
		<div class="flex-shrink-0 w-10 h-10">
			<img class="w-full h-full rounded-full" src="https://i.imgur.com/GCBVgXD.jpeg" alt="" />
		</div>

		<div class="bg-gray-700 w-full rounded-xl">
			<form class="w-full">
				<textarea class="w-full h-52 text-white text-xl bg-gray-700 rounded-xl p-2" placeholder="What's happening?"></textarea>
			</form>
			<div class="relative flex-shrink-0 w-12 h-12 m-2">
				<img class="w-full h-full" src="https://i.imgur.com/GCBVgXD.jpeg" alt="" />
				<span class=" absolute -right-2 -top-1">
					

				</span>
			</div>
		</div>
	</div>

	<div class="flex pt-4 pl-4 items-center justify-between">
		<div class="flex gap-4">
			<PicsIcon width=24 height=24/>
			<LocationIcon width=24 height=24/>			
		</div>
		<div class="flex">
			
			<div class="relative flex-shrink-0 w-10/12 px-2 text-white absolute -right-2">
				<button class="w-40 p-2 bg-blue-500 hover:bg-opacity-40 rounded-full font-bold border border-gray-700 ">
					Share </button>
			</div>
		</div>
	</div>

</div>

</dialog>