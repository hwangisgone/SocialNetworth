<script lang='ts'>
	import SearchIcon from '$lib/icon/search.svg?component';

	export let term = "";
</script>

<!-- Search Bar -->
<div class="relative m-2 w-full px-5">
	<a rel="external" href={"/search?term=" + term} class="absolute text-gray-600 flex items-center pl-4 h-full text-base-content cursor-pointer">
	  <svg xmlns="http://www.w3.org/2000/svg" class="icon icon-tabler icon-tabler-mail" width="18" height="18" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" fill="currentColor" stroke-linecap="round" stroke-linejoin="round">
		<path d="M21.53 20.47l-3.66-3.66C19.195 15.24 20 13.214 20 11c0-4.97-4.03-9-9-9s-9 4.03-9 9 4.03 9 9 9c2.215 0 4.24-.804 5.808-2.13l3.66 3.66c.147.146.34.22.53.22s.385-.073.53-.22c.295-.293.295-.767.002-1.06zM3.5 11c0-4.135 3.365-7.5 7.5-7.5s7.5 3.365 7.5 7.5-3.365 7.5-7.5 7.5-7.5-3.365-7.5-7.5z"></path>
	  </svg>
	</a>
	<input class="w-full bg-gray-200 dark:bg-gray-600 border-gray-200 dark:border-gray-400 focus:bg-gray-100 dark:focus:bg-gray-900 focus:outline-none focus:border focus:border-blue-200 font-normal h-9 flex items-center pl-12 text-sm rounded-full border shadow" placeholder="Search ..." bind:value={term}>
</div>