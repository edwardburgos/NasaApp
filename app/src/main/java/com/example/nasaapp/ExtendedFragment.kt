package com.example.nasaapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class ExtendedFragment: Fragment() {

    // private val viewModel: RecipeListViewModel by viewModels()

//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        return ComposeView(requireContext()).apply {
//            setContent {
//
//                val recipes = viewModel.recipes.value
//
//                val query = viewModel.query.value
//
//                Column {
//                    Surface(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                        ,
//                        color = Color.White,
//                        elevation = 8.dp,
//                    ){
//                        Column{
//                            Row(modifier = Modifier.fillMaxWidth()){
//                                TextField(
//                                    modifier = Modifier
//                                        .fillMaxWidth(.9f)
//                                        .padding(8.dp)
//                                    ,
//                                    value = query,
//                                    onValueChange = {
//                                        viewModel.onQueryChanged(it)
//                                    },
//                                    label = {
//                                        Text(text = "Search")
//                                    },
//                                    keyboardOptions = KeyboardOptions(
//                                        keyboardType = KeyboardType.Text,
//                                        imeAction = ImeAction.Done,
//                                    ),
//                                    leadingIcon = {
//                                        Icon(Icons.Filled.Search)
//                                    },
//                                    onImeActionPerformed = { action, softKeyboardController ->
//                                        if (action == ImeAction.Done) {
//                                            viewModel.newSearch(query)
//                                            softKeyboardController?.hideSoftwareKeyboard()
//                                        }
//                                    },
//                                    textStyle = TextStyle(color = MaterialTheme.colors.onSurface),
//                                    backgroundColor = MaterialTheme.colors.surface
//                                )
//                            }
//                            ScrollableRow(
//                                modifier = Modifier
//                                    .padding(start = 8.dp, bottom = 8.dp)
//                                ,
//                            ) {
//                                for(category in getAllFoodCategories()){
//                                    FoodCategoryChip(
//                                        category = category.value,
//                                        onExecuteSearch = {
//                                            viewModel.onQueryChanged(it)
//                                            viewModel.newSearch(it)
//                                        },
//                                    )
//                                }
//                            }
//                        }
//                    }
//
//                    LazyColumn {
//                        itemsIndexed(
//                            items = recipes
//                        ){index, recipe ->
//                            RecipeCard(recipe = recipe, onClick = {})
//                        }
//                    }
//                }
//            }
//        }
   // }

}
