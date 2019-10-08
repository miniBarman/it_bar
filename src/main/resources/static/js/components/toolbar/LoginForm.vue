<template>
	<v-row justify="center">
		<v-dialog v-model="dialog" persistent max-width="600px">
			<template v-slot:activator="{ on }">
				<v-btn color="primary" dark v-on="on">Login</v-btn>
			</template>
			<v-card>
				<v-card-title>
					<span class="headline">Login</span>
				</v-card-title>
				<v-card-text>
					<v-form action="/login" method="post">
						<v-text-field type="text" name="username" label="Username">
							<v-icon>person</v-icon>
						</v-text-field>
						<v-text-field type="password" name="password" label="Password" >
							<v-icon>lock</v-icon>
						</v-text-field>
						<input type="hidden" name="_csrf" v-bind:value=csrfToken />
						<input type="submit" value="Sign In"/>
					</v-form>
				</v-card-text>
				<v-card-actions>
					<div class="flex-grow-1"/>
					<v-btn color="blue darken-1" text type="submit">Login</v-btn>
					<v-btn color="blue darken-1" text @click="dialog = false">Close</v-btn>
				</v-card-actions>
			</v-card>
		</v-dialog>
	</v-row>
</template>

<script>
    export default {
        data() {
            return{
                dialog: false,
				csrfToken: csrfToken
            }
        },
        methods: {
            submit () {
                this.$v.$touch()
            }
        }
    }
</script>

<style>
</style>