package com.veeyikpong.easyfragment

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

class FragmentContainer @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    interface FragmentListener {
        fun onFragmentShow(f: Fragment)
        fun onFragmentDestroyed(f: Fragment)
    }

    private var mListener: FragmentListener? = null
    private var mFragmentManager: FragmentManager = ((context) as AppCompatActivity).supportFragmentManager
    lateinit var currentFragment: Fragment

    //Animations
    private var mEnterAnimation: Int = 0
    private var mExitAnimation: Int = 0

    init {
        val attributeArray = context.obtainStyledAttributes(
            attrs,
            R.styleable.fragmentContainer
        )

        mEnterAnimation = attributeArray.getResourceId(R.styleable.fragmentContainer_enterAnimation, 0)
        mExitAnimation = attributeArray.getResourceId(R.styleable.fragmentContainer_exitAnimation, 0)

        mFragmentManager.registerFragmentLifecycleCallbacks(object : FragmentManager.FragmentLifecycleCallbacks() {
            override fun onFragmentActivityCreated(fm: FragmentManager, f: Fragment, savedInstanceState: Bundle?) {
                super.onFragmentActivityCreated(fm, f, savedInstanceState)
                super.onFragmentResumed(fm, f)
                if (mListener != null) {
                    mListener!!.onFragmentShow(f)
                }
                currentFragment = f
            }

            override fun onFragmentViewDestroyed(fm: FragmentManager, f: Fragment) {
                super.onFragmentViewDestroyed(fm, f)
                if (mListener != null) {
                    mListener!!.onFragmentDestroyed(f)
                }
            }
        }, true)
    }

    fun addFragmentListener(listener: FragmentListener) {
        this.mListener = listener
    }

    fun setEnterAnimation(animation: Int) {
        this.mEnterAnimation = animation
    }

    fun setExitAnimation(animation: Int) {
        this.mExitAnimation = animation
    }

    fun replaceFragment(fragment: Fragment) {
        mFragmentManager
            .beginTransaction()
            .replace(this.id, fragment)
            .disallowAddToBackStack()
            .commitAllowingStateLoss()

        currentFragment = fragment
    }

    fun addFragment(fragment: Fragment, bundle: Bundle = Bundle()) {
        fragment.arguments = bundle

        val transaction = mFragmentManager
            .beginTransaction()
            .replace(this.id, fragment, fragment.javaClass.simpleName)
            .addToBackStack(fragment.javaClass.simpleName)

        transaction.setCustomAnimations(mEnterAnimation, mExitAnimation)

        transaction.commit()
    }

    fun back(): Boolean {
        val backstackCount = mFragmentManager.backStackEntryCount
        if (backstackCount > 1) {
            mFragmentManager.popBackStackImmediate()
            return true
        }

        return false
    }
}