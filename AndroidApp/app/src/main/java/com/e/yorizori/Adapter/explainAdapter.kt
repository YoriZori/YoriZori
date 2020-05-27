package com.e.yorizori.Adapter

/*
class explainAdapter(val context: Context, val recipe: Recipe) : BaseAdapter()
{

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = LayoutInflater.from(context).inflate(R.layout.custom_explain, null)

        val howto = view.findViewById<TextView>(R.id.howto)
        val recipe1 = Recipe("계란볶음밥",
            arrayOf(
                "1. 기름을 두른다",
                "2. 계란을 깨서 넣고 마구 젓는다",
                "3. 밥을 넣어 잘 풀어준다",
                "4. 밥이 모두 풀어지면 소금간을 한다",
                "5. 완성!"
            ),
            arrayOf("5분 완성", "밥과 계란", "응용 가능"),
            arrayOf("대충 사진"),
            "내가 쓴거 아님",
            arrayOf(Pair("밥", "1그릇"), Pair("계란", "1개"), Pair("식용유", "10스푼")),
            arrayOf(3, 19, 20),
            43).recipe

        howto.text = recipe1.toString()

        return view
    }

    override fun getItem(position: Int): Any {
        return Recipe().recipe
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return 1
    }

}

*/





/*
class explainAdapter : BaseAdapter() {

    private var recipeList = ArrayList<Recipe>()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        var view = convertView
        val context = parent?.context

        if (view == null) {
            val inflater =
                context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.custom_explain, parent, false)
        }

        val howto = view?.findViewById<TextView>(R.id.howto)
        val recipe_ListviewItem = recipeList[position]


        return view!!
    }

    override fun getItem(position: Int): Any {
        return recipeList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return recipeList.size
    }
}
*/