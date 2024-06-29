<script>
	import { page } from '$app/stores';
	import { getUser } from '$lib/userapi';

	let currentUser = {};

	async function loadAll() {
		currentUser = await getUser($page.params.userid);
	}

	loadAll();
</script>

<!--Profile card start-->
<div class="p-4">
<div class="card bg-opacity-1 w-full
rounded-lg shadow-lg flex flex-col"> 
	<div class="card-image"> 
		<img src="https://i.imgur.com/fjXFX93.jpeg"
		alt="Image" 
		class="w-full h-48 object-cover rounded-t-lg" /> 
	</div> 
	<div class="flex"> 
		<img src={currentUser.avatarUrl ? currentUser.avatarUrl : "https://i.imgur.com/xcLTrkV.png"}
		alt=""
		class="float-left z-10 w-24 h-24 relative ml-16 -mt-16  
		block rounded-full border-2 border-gary-800  
		transition-transform duration-400  
		transform hover:scale-110 object-cover" /> 
	</div> 


	<div class="card-content pt-2 pb-4 pl-8 pr-16"> 
	<form class="grid grid-cols-5 gap-4 justify-items-end items-center">

		<label class="text-right font-semibold">Name:</label>
		<input type="text" class="col-span-4 w-full input input-sm input-bordered rounded-lg"
			placeholder="Your Name Here" required 
			bind:value={currentUser.name}/>

		<label class="text-left font-semibold">Name Tag:</label>
		<input type="text" class="col-span-4 w-full input input-sm input-bordered rounded-lg" 
			placeholder="nameTag" required
			bind:value={currentUser.nameTag}/>

		<label class="text-left font-semibold">Photo URL:</label>
		<input type="text" class="col-span-4 w-full input input-sm input-bordered rounded-lg" 
			placeholder="https://i.imgur.com/9l1A4OS.jpeg" required
			bind:value={currentUser.avatarUrl}/>

		<label class="text-right font-semibold">Bio:</label>
		<textarea class="col-span-4 w-full textarea textarea-sm textarea-bordered rounded-lg" 
			placeholder="No bio..." required
			bind:value={currentUser.bio}/>
			
		<button type="submit" class="col-span-5 btn rounded-lg btn-primary px-8 mr-8">Save</button>
	</form>
	</div>  
<!--Profile card end-->
</div>
</div>
