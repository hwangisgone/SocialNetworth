
<script lang='ts'>
	import GoogleLogo from '$lib/logo/google-g-logo.svg?component';
	import GithubLogo from '$lib/logo/github-mark-white.svg?component';
	import FacebookLogo from '$lib/logo/facebook-primary.svg?component';
	import PurrPostLogo from '$lib/logo/purrpost-logo.svg?component';

	import { login } from '$lib/authapi';

	// Function to log in and save the token
	let loginData = {
		nameTag: "",
		password: ""
	}


</script>


<div class="flex min-h-screen items-center justify-center rounded-3xl">
	<div class="nd-background w-2/3 min-h-1/2 border border-gray-900 rounded-3xl">

		<form class="mx-4 sm:mx-24 md:mx-34 lg:mx-56 mx-auto  flex items-center space-y-4 py-16 font-semibold text-gray-500 flex-col">
			<PurrPostLogo class="text-primary" width="120" height="120"/>

			<h1 class="text-2xl">Sign in</h1>
			<p class="text-xs pt-6 pb-16">Enter your email and password</p>


			<div class="w-full transform border-b bg-transparent duration-300 text-sm focus-within:border-indigo-500">
			  <input
				type="text"
				placeholder="Email or Username"
				class="w-full border-none bg-transparent outline-none placeholder:italic focus:outline-none"
				bind:value={loginData.nameTag}
			  />
			</div>

			<div class="w-full transform border-b bg-transparent duration-300 text-sm focus-within:border-indigo-500">
			  <input
				type="password"
				placeholder="Password"
				class="w-full border-none bg-transparent outline-none placeholder:italic focus:outline-none"
				bind:value={loginData.password}

				autocomplete="on"
			  />
			</div>
			
			<button class="w-full p-2 bg-gray-50 rounded-full font-bold text-gray-900 border border-gray-700 "  
				on:click|preventDefault={() => login(loginData) }> Log in</button>


			<p class="text-sm">Don't have an account? 
			<a class="font-semibold text-sky-700" href="./register">Registrate</a> </p>
			<div class="w-full flex items-center space-x-4">
				<hr class="w-full border-b" />
				<div class="text-sm text-gray-400">Or</div>
				<hr class="w-full border-b" />
			</div>
			<div class="mt-5 flex justify-center gap-3 ">
				<GoogleLogo width="40" height="40"/>
				<GithubLogo class="text-base-content" width="40" height="40"/>
				<FacebookLogo width="40" height="40"/>
			</div>


		</form>

	</div>

</div>